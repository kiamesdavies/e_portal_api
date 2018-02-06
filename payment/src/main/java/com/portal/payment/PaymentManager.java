/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.payment;

import akka.actor.ActorSystem;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.markserrano.jsonquery.jpa.enumeration.OrderEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.github.markserrano.jsonquery.jpa.service.IFilterService;
import com.github.markserrano.jsonquery.jpa.specifier.Order;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;
import com.google.common.collect.ImmutableMap;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.OrderSpecifier;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.portal.applications.ApplicationManager;
import com.portal.commons.events.Sms;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.ApplicationSummary;
import com.portal.commons.models.Category;
import com.portal.commons.models.GeneralMapper;
import com.portal.commons.models.ManualTransaction;
import com.portal.commons.models.ManualTransactionView;
import com.portal.commons.models.OnlineTransaction;
import com.portal.commons.models.OnlineTransactionView;
import com.portal.commons.models.Payment;
import com.portal.commons.util.CycleAvoidingMappingContext;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
import com.portal.configuration.IMessageTemplate;
import com.portal.entities.JpaAppUser;
import com.portal.entities.JpaApplicationSummary;
import com.portal.entities.JpaCategory;
import com.portal.entities.JpaManualTransaction;
import com.portal.entities.JpaManualTransactionView;
import com.portal.entities.JpaOnliePaymentTransactionRawReponse;
import com.portal.entities.JpaOnlineTransaction;
import com.portal.entities.JpaOnlineTransactionView;
import com.portal.entities.JpaPayment;
import com.portal.entities.QJpaAppUser;
import com.portal.entities.QJpaApplicationSummary;
import com.portal.entities.QJpaOnlineTransaction;
import com.portal.entities.QJpaCategory;
import com.portal.entities.QJpaManualTransaction;
import com.portal.entities.QJpaPayment;
import com.portal.user_management.AppUserManager;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.inject.Inject;
import mock.springframework.data.domain.Page;
import mock.springframework.data.domain.PageRequest;
import mock.springframework.data.domain.Pageable;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.JPAApi;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import scala.concurrent.duration.Duration;

/**
 *
 * @author poseidon
 */
public class PaymentManager {

    @Inject
    private ActorSystem actorSystem;
    @Inject
    JPAApi jPAApi;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    IFilterService filterService;

    @Inject
    ApplicationManager applicationManager;

    @Inject
    AppUserManager appUserManager;

    @Inject
    private IMessageTemplate messageTemplate;

    @Inject
    private WSClient ws;

    public void saveOnlineResponse(String url, String response, Date requestTime) {
        try {
            JpaOnliePaymentTransactionRawReponse jpaOnliePaymentTransactionRawReponse = new JpaOnliePaymentTransactionRawReponse(null, response, url);
            jpaOnliePaymentTransactionRawReponse.setDateCreated(new Date());
            jpaOnliePaymentTransactionRawReponse.setRequestReceivedTime(requestTime);
            jPAApi.em().persist(jpaOnliePaymentTransactionRawReponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Category getCategory(String categoryId) throws ResourceNotFound {
        QJpaCategory qjc = QJpaCategory.jpaCategory;
        JpaCategory fetchOne = new JPAQueryFactory(jPAApi.em()).selectFrom(qjc).where(qjc.categoryId.equalsIgnoreCase(categoryId)).fetchOne();

        if (fetchOne == null) {
            throw new ResourceNotFound(String.format("Category %s not found", categoryId));
        }
        return GeneralMapper.INSTANCE.jpaCategoryToCategory(fetchOne, new CycleAvoidingMappingContext());
    }

    public Payment getPayment(String paymentId) throws ResourceNotFound {
        QJpaPayment qjp = QJpaPayment.jpaPayment;
        JpaPayment fetchOne = new JPAQueryFactory(jPAApi.em()).selectFrom(qjp).where(qjp.paymentId.equalsIgnoreCase(paymentId)).fetchOne();

        if (fetchOne == null) {
            throw new ResourceNotFound(String.format("Payment %s not found", paymentId));
        }
        return GeneralMapper.INSTANCE.jpaPaymentToGeneratedPayment(fetchOne, new CycleAvoidingMappingContext());
    }

    public OnlineTransaction getOnlineTransaction(String transactionId) throws ResourceNotFound {

        return GeneralMapper.INSTANCE.jpaOnlineTransactionToGeneratedOnlineTransaction(getJpaOnlineTransaction(transactionId), new CycleAvoidingMappingContext());
    }

    public JpaOnlineTransaction getJpaOnlineTransaction(String transactionId) throws ResourceNotFound {
        QJpaOnlineTransaction qjp = QJpaOnlineTransaction.jpaOnlineTransaction;
        QJpaPayment qJpaPayment = QJpaPayment.jpaPayment;
        JpaOnlineTransaction fetchOne = new JPAQueryFactory(jPAApi.em()).selectFrom(qjp).join(qjp.paymentId, qJpaPayment).where(qjp.transactionId.equalsIgnoreCase(transactionId)).fetchOne();

        if (fetchOne == null) {
            throw new ResourceNotFound(String.format("OnlineTransaction %s not found", transactionId));
        }
        return fetchOne;
    }

    public Optional<Payment> getMostRecentPayment(String appUserId) {

        Optional<JpaPayment> mostRecentPayment = getMostRecentPaymentJpa(appUserId);
        Payment v = !mostRecentPayment.isPresent() ? null : GeneralMapper.INSTANCE.jpaPaymentToGeneratedPayment(mostRecentPayment.get(), new CycleAvoidingMappingContext());
        return Optional.ofNullable(v);

    }

    public Optional<JpaPayment> getMostRecentPaymentJpa(String appUserId) {
        QJpaPayment qjp = QJpaPayment.jpaPayment;
        JpaPayment fetchFirst = new JPAQueryFactory(jPAApi.em()).selectFrom(qjp).where(qjp.appUserId.appUserId.equalsIgnoreCase(appUserId)).orderBy(qjp.dateInitialized.desc()).fetchFirst();

        return Optional.ofNullable(fetchFirst);

    }

    public Payment initializePayment(String appUserId, String paymentIntendedType, String bankName, String bankTeller) throws ResourceNotFound {

        return GeneralMapper.INSTANCE.jpaPaymentToGeneratedPayment(initializePaymentJpa(appUserId, paymentIntendedType, bankName, bankTeller), new CycleAvoidingMappingContext());
    }

    public JpaPayment initializePaymentJpa(String appUserId, String paymentIntendedType, String bankName, String bankTeller) throws ResourceNotFound {

        Optional<JpaPayment> mostRecentPayment = getMostRecentPaymentJpa(appUserId);

        if (mostRecentPayment.isPresent() && !mostRecentPayment.get().getPaid() && Objects.isNull(mostRecentPayment.get().getDatePaid())) {
            return mostRecentPayment.get();
        }

        ApplicationSummary applicationSummary = applicationManager.getApplicationSummary(appUserId);

        Category category = getCategory(applicationSummary.getCategory());

        JpaPayment jpaPayment = new JpaPayment(java.util.UUID.randomUUID().toString(), new Date(), false, category.getAmount(), paymentIntendedType);

        jpaPayment.setAppUserId(new JpaAppUser(appUserId));

        jpaPayment.setBankName(bankName);
        jpaPayment.setBankTeller(bankTeller);

        jpaPayment.setCategoryId(new JpaCategory(category.getCategoryId()));

        jPAApi.em().persist(jpaPayment);

        return jpaPayment;
    }

    public List<Payment> getAllExpiredPayment(LocalDate expiryDate) {
        QJpaApplicationSummary qjas = QJpaApplicationSummary.jpaApplicationSummary;
        QJpaPayment qjp = QJpaPayment.jpaPayment;
        QJpaAppUser qjau = QJpaAppUser.jpaAppUser;
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDateTime todayMidnight = LocalDateTime.of(expiryDate, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        Date todayMidnightDate = Date.from(todayMidnight.atZone(ZoneId.of("UTC")).toInstant());
        Date tomorrowMidnightDate = Date.from(tomorrowMidnight.atZone(ZoneId.of("UTC")).toInstant());
        List<String> paymentIds = new JPAQueryFactory(jPAApi.em()).from(qjas).select(qjas.paymentId).where(qjas.datePaid.isNotNull(), qjas.datePaid.before(tomorrowMidnightDate), qjas.datePaid.after(todayMidnightDate)).fetch();

        return new JPAQueryFactory(jPAApi.em()).selectFrom(qjp).join(qjp.appUserId, qjau).where(qjp.paymentId.in(paymentIds)).fetch().stream().map(f -> GeneralMapper.INSTANCE.jpaPaymentToGeneratedPayment(f, new CycleAvoidingMappingContext())).collect(Collectors.toList());
    }

    public JqgridResponse<OnlineTransactionView> getOnlineTransactions(Boolean search, String filters,
            Integer page, Integer rows, String sidx, String sord) {
        filters = filters == null || filters == "" || !search ? QueryUtil.INIT_FILTER : filters;
        Order order = new Order(JpaOnlineTransactionView.class);
        OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
        JsonFilter jsonFilter = new JsonFilter(filters);
        BooleanBuilder build = filterService.getJsonBooleanBuilder(JpaOnlineTransactionView.class).build(jsonFilter);
        if (rows == null) {
            rows = filterService.count(build, JpaOnlineTransactionView.class, orderSpecifier).intValue();
        }
        Pageable pageable = new PageRequest(page >= 1 ? page - 1 : 0, rows > 0 ? rows : 1);
        Page<JpaOnlineTransactionView> results = filterService.readAndCount(build, pageable, JpaOnlineTransactionView.class, orderSpecifier);
        JqgridResponse<OnlineTransactionView> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(GeneralMapper.INSTANCE::jpaOnlineTransactionViewToOnlineTransactionView).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }

    public OnlineTransaction billPayment(String paymentId) throws ResourceNotFound {
        Payment payment = getPayment(paymentId);
        JpaOnlineTransaction onlineTransaction = new JpaOnlineTransaction(java.util.UUID.randomUUID().toString(), new Date(), payment.getAmountToPay());
        onlineTransaction.setPaymentId(new JpaPayment(paymentId));
        jPAApi.em().persist(onlineTransaction);

        return GeneralMapper.INSTANCE.jpaOnlineTransactionToGeneratedOnlineTransaction(onlineTransaction, new CycleAvoidingMappingContext());
    }

    public CompletionStage<JsonNode> retryTransaction(String transactionId, boolean completePaymentOnlyIfSucessful) throws Exception {
        String hash = generateHashedStringSHA512(String.format("%s%s%s", transactionId, EnvironMentVariables.REMITTA_APIKEY, EnvironMentVariables.REMITTA_MERCHANTID));
        return ws.url(EnvironMentVariables.REMITTA_CHECKSTATUSURL + "/" + EnvironMentVariables.REMITTA_MERCHANTID + "/" + transactionId + "/" + hash + "/orderstatus.reg").get().thenApply(WSResponse::asJson).thenCompose(fn -> {
            try {
                return completeInstantOnlineTransaction(fn.get("RRR").asText(), completePaymentOnlyIfSucessful);
            } catch (Exception ex) {
                ex.printStackTrace();
                return CompletableFuture.completedFuture(fn);
            }
        });
    }

    public CompletionStage<JsonNode> completeInstantOnlineTransaction(String rrr, boolean completePaymentOnlyIfSucessful) throws Exception {
        String hash = generateHashedStringSHA512(String.format("%s%s%s", rrr, EnvironMentVariables.REMITTA_APIKEY, EnvironMentVariables.REMITTA_MERCHANTID));
        return ws.url(EnvironMentVariables.REMITTA_CHECKSTATUSURL + "/" + EnvironMentVariables.REMITTA_MERCHANTID + "/" + rrr + "/" + hash + "/status.reg").get().thenApply(WSResponse::asJson).thenApply(ft -> {

            jPAApi.withTransaction(() -> {
                RemitaResponse fn = null;
                try {
                    fn = objectMapper.treeToValue(ft, RemitaResponse.class);
                } catch (Exception e) {

                }
                if (fn == null) {
                    return;
                }
                JpaOnlineTransaction jpaOnlineTransaction = null;

                try {

                    jpaOnlineTransaction = getJpaOnlineTransaction(fn.getOrderId());
                    //the transaction has already been attended to
                    if (Objects.nonNull(jpaOnlineTransaction.getSucessful()) && Objects.nonNull(jpaOnlineTransaction.getDatePayed())) {
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
                JpaPayment paymentId = jpaOnlineTransaction.getPaymentId();
                if (jpaOnlineTransaction != null && ((!completePaymentOnlyIfSucessful) || (completePaymentOnlyIfSucessful && (fn.getStatus().equalsIgnoreCase("00") || fn.getStatus().equalsIgnoreCase("01"))))) {
                    jpaOnlineTransaction.setSucessful(fn.getStatus().equalsIgnoreCase("00") || fn.getStatus().equalsIgnoreCase("01"));
                    jpaOnlineTransaction.setResponseMessage(fn.getMessage());
                    jpaOnlineTransaction.setResponseCode(fn.getStatus());
                    jpaOnlineTransaction.setDatePayed(new Date());
                    jpaOnlineTransaction.setRrr(rrr);
                    jpaOnlineTransaction.setAmountPaid(paymentId.getAmountToPay());

                    if (jpaOnlineTransaction.getSucessful()) {
                        paymentId.setAmountPaid(paymentId.getAmountToPay());
                        paymentId.setDatePaid(new Date());
                        paymentId.setPaymentType("online");
                        paymentId.setPaid(true);
                        paymentId.setBankTeller(rrr);
                        paymentId.setBankName("remitta::" + EnvironMentVariables.REMITTA_MERCHANTID);
                        LocalDateTime localDateTime = LocalDateTime.now().plusYears(3);
                        paymentId.setExpiryDate(Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant()));

                    }
                }
                jPAApi.em().merge(jpaOnlineTransaction);
                jPAApi.em().merge(paymentId);

            });
            return ft;
        });
    }

    public CompletionStage<JsonNode> completeBatchOnlineTransaction(ResponseObject responseObject) throws Exception {

        String hash = generateHashedStringSHA512(String.format("%s%s%s", responseObject.getRrr(), EnvironMentVariables.REMITTA_APIKEY, EnvironMentVariables.REMITTA_MERCHANTID));
        return ws.url(EnvironMentVariables.REMITTA_CHECKSTATUSURL + "/" + EnvironMentVariables.REMITTA_MERCHANTID + "/" + responseObject.getRrr() + "/" + hash + "/status.reg").get().thenApply(WSResponse::asJson).thenApply(ft -> {

            jPAApi.withTransaction(() -> {
                RemitaResponse fn = null;
                try {
                    fn = objectMapper.treeToValue(ft, RemitaResponse.class);
                } catch (Exception e) {

                }
                if (fn == null) {
                    return;
                }
                JpaOnlineTransaction jpaOnlineTransaction = null;

                try {

                    jpaOnlineTransaction = getJpaOnlineTransaction(fn.getOrderId());
                    //the transaction has already been attended to
                    if (Objects.nonNull(jpaOnlineTransaction.getSucessful()) && Objects.nonNull(jpaOnlineTransaction.getDatePayed())) {
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
                JpaPayment paymentId = jpaOnlineTransaction.getPaymentId();
                jpaOnlineTransaction.setSucessful(true);
                jpaOnlineTransaction.setResponseMessage(String.format("%s %s", responseObject.getPayerName(), responseObject.getPayerPhoneNumber()));
                jpaOnlineTransaction.setResponseCode("0001");
                jpaOnlineTransaction.setDatePayed(new Date());
                jpaOnlineTransaction.setAmountPaid(Double.valueOf(responseObject.getAmount()));

                paymentId.setDatePaid(new Date());
                paymentId.setAmountPaid(Double.valueOf(responseObject.getAmount()));
                paymentId.setPaymentType("online-batch");
                paymentId.setPaid(true);
                paymentId.setBankTeller(responseObject.getRrr());
                paymentId.setBankName(responseObject.getBank());
                LocalDateTime localDateTime = LocalDateTime.now().plusYears(3);
                paymentId.setExpiryDate(Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant()));

                jPAApi.em().merge(jpaOnlineTransaction);
                jPAApi.em().merge(paymentId);

            });
            return objectMapper.convertValue(responseObject, JsonNode.class);
        });
    }

    public JqgridResponse<ManualTransactionView> getManualTransactions(Boolean search, String filters,
            Integer page, Integer rows, String sidx, String sord) {
        filters = filters == null || filters == "" || !search ? QueryUtil.INIT_FILTER : filters;
        Order order = new Order(JpaManualTransactionView.class);
        OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
        JsonFilter jsonFilter = new JsonFilter(filters);
        BooleanBuilder build = filterService.getJsonBooleanBuilder(JpaManualTransactionView.class).build(jsonFilter);
        if (rows == null) {
            rows = filterService.count(build, JpaManualTransactionView.class, orderSpecifier).intValue();
        }
        Pageable pageable = new PageRequest(page >= 1 ? page - 1 : 0, rows > 0 ? rows : 1);
        Page<JpaManualTransactionView> results = filterService.readAndCount(build, pageable, JpaManualTransactionView.class, orderSpecifier);
        JqgridResponse<ManualTransactionView> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(GeneralMapper.INSTANCE::jpaManualTransactionViewToManualTransactionView).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }
    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final Function<String, Date> STRING_DATE_TO_DATE = (s) -> {
        if (StringUtils.isNoneBlank(s)) {
            try {
                return DATEFORMAT.parse(s.trim());
            } catch (ParseException e) {
            }
        }
        return new Date();
    };

    public void createManualTranscation(String byAppUserId, String teacherRegNumber, String bankName, String bankTeller, String amountPaid, String datePaidString) {

        teacherRegNumber = teacherRegNumber.trim();
        amountPaid = amountPaid.trim();
        bankTeller = bankTeller.trim();
        QJpaManualTransaction qjmt = QJpaManualTransaction.jpaManualTransaction;
        if (StringUtils.isNoneBlank(bankTeller)) {
            String fetchFirst = new JPAQueryFactory(jPAApi.em()).from(qjmt).select(qjmt.bankTeller).where(qjmt.bankTeller.equalsIgnoreCase(bankTeller)).fetchFirst();
            //transaction exists
            if (StringUtils.isNoneBlank(fetchFirst)) {
                return;
            }
        }

        QJpaApplicationSummary qjas = QJpaApplicationSummary.jpaApplicationSummary;
        JpaApplicationSummary applicationSummary = new JPAQueryFactory(jPAApi.em()).selectFrom(qjas).where(qjas.userName.equalsIgnoreCase(teacherRegNumber)).fetchOne();

        JpaManualTransaction manualTransaction = new JpaManualTransaction(java.util.UUID.randomUUID().toString(), teacherRegNumber, new Date(), false);
        manualTransaction.setCreatedByAppUserId(new JpaAppUser(byAppUserId));
        manualTransaction.setCreditedAccount(false);
        manualTransaction.setBankName(bankName);
        manualTransaction.setBankTeller(bankTeller);
        if (StringUtils.isNoneBlank(applicationSummary.getAppUserId())) {
            try {
                JpaPayment payment = this.initializePaymentJpa(applicationSummary.getAppUserId(), "manual", bankName, bankTeller);
                manualTransaction.setPaymentId(payment);
                if (Double.valueOf(amountPaid) >= payment.getAmountToPay()) {
                    Date datePaid = STRING_DATE_TO_DATE.apply(datePaidString);
                    payment.setDatePaid(datePaid);
                    payment.setAmountPaid(Double.valueOf(amountPaid));
                    payment.setPaymentType("manual");
                    payment.setPaid(true);

                    LocalDateTime localDateTime = LocalDateTime.ofInstant(datePaid.toInstant(), ZoneId.of("UTC")).plusYears(3);

                    payment.setExpiryDate(Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant()));

                    manualTransaction.setClaimed(true);
                    manualTransaction.setDateClaimed(new Date());
                    manualTransaction.setCreditedAccount(true);
                    manualTransaction.setAmountPaid(Double.valueOf(amountPaid));
                    manualTransaction.setDatePaid(new Date());
                    jPAApi.em().merge(payment);

                    String msgTemplate = messageTemplate.getMessageTemplate(IMessageTemplate.MessageType.PAYMENT_CLAIMED).getSmsTemplate();
                    String name = String.format("%s %s", Objects.toString(applicationSummary.getFirstName(), ""), Objects.toString(applicationSummary.getSurName(), ""));
                    String body = messageTemplate.parseMessageTemplate(msgTemplate, ImmutableMap.of("name", name.trim(), "teacherRegNumber", applicationSummary.getUserName()));
                    Sms sms = new Sms(applicationSummary.getMobileNumber(), "TRCN", body);
                    actorSystem.scheduler().scheduleOnce(Duration.create(1, TimeUnit.SECONDS),
                            () -> actorSystem.eventStream().publish(sms), actorSystem.dispatcher());
                } else {
                    manualTransaction.setProcessingMessage(String.format("Amount to pay %f is less than amount paid %s", payment.getAmountToPay(), amountPaid));
                }
            } catch (ResourceNotFound ex) {
                manualTransaction.setProcessingMessage(String.format("Could not initialize payment due to %s ", ex.getMessage()));
            } catch (Exception ex) {
                manualTransaction.setProcessingMessage(String.format("Failed to process transaction due to %s", ex.getMessage()));
            }
        } else {
            manualTransaction.setProcessingMessage(String.format("No teacher for %s ", teacherRegNumber));
        }
        jPAApi.em().persist(manualTransaction);
    }

    private static final SimpleDateFormat MONTH_DATEFORMAT = new SimpleDateFormat("yyyy-MM");

    public String getCertificate(String appUserId) throws ResourceNotFound {
        ApplicationSummary applicationSummary = applicationManager.getApplicationSummary(appUserId);
        Optional<JpaPayment> mostRecentPaymentJpa = getMostRecentPaymentJpa(appUserId);
        if (mostRecentPaymentJpa.isPresent()) {
            try {
                if (StringUtils.isNoneBlank(mostRecentPaymentJpa.get().getCertificatePath()) && new File(EnvironMentVariables.STORAGE_PATH + mostRecentPaymentJpa.get().getCertificatePath()).exists()) {
                    return EnvironMentVariables.STORAGE_PATH + mostRecentPaymentJpa.get().getCertificatePath();
                }
                String certificatePath = MONTH_DATEFORMAT.format(new Date()) + "/" + RandomStringUtils.randomAlphanumeric(30) + ".pdf";
                String fullPath = EnvironMentVariables.STORAGE_PATH + certificatePath;
                new File(fullPath).getParentFile().mkdirs();
                LocalDateTime localDateTime = LocalDateTime.ofInstant(mostRecentPaymentJpa.get().getDatePaid().toInstant(), ZoneId.of("UTC")).plusMonths(2);
                Date expiryDate = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
                String msgTemplate = messageTemplate.getMessageTemplate(IMessageTemplate.MessageType.CERTICIATE).getMailTemplate();
                String html = messageTemplate.parseMessageTemplate(msgTemplate, ImmutableMap.of("expiryDate", new SimpleDateFormat("EEE, d MMM yyyy").format(expiryDate), "firstName", applicationSummary.getFirstName(), "surName", applicationSummary.getSurName(), "middleName", applicationSummary.getMiddleName(), "regNo", applicationSummary.getUserName()));
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.withHtmlContent(html, "file://" + EnvironMentVariables.STORAGE_PATH);

                try (FileOutputStream outputStream = new FileOutputStream(fullPath)) {
                    builder.toStream(outputStream);
                    builder.run();
                }
                mostRecentPaymentJpa.get().setCertificatePath(certificatePath);
                jPAApi.em().merge(mostRecentPaymentJpa.get());
                return fullPath;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaymentManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PaymentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Get price of a category
     *
     * Add column amount to pay
     *
     *
     *
     * Initialize payment for appUser regardless of previous payments, with
     * amount to pay Get most recent payment for appUser finalize Payment with
     * payment type Get every user last payment that has expired 3 years ago or
     * more
     *
     *
     * Get all online transactions create online transaction for a payment that
     * date paid is null update online transaction retry online transaction
     *
     * Get all manual transactions
     *
     * create manual transaction, get the user using the registration number and
     * create a payment for the user if he doesn't a payment where date paid is
     * null,
     *
     * reclaim manual transaction
     *
     *
     * A manual could fail for 3 reasons if the user found does not have an
     * application or the application has no category, or the amount to pay does
     * not match amount paid
     *
     */
    public static String generateHashedStringSHA512(String echoString) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(echoString.getBytes());
        byte[] echoData = messageDigest.digest();
        String out = "";
        StringBuilder sb = new StringBuilder();
        for (byte element : echoData) {
            sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
        }
        out = sb.toString();
        return out;
    }
}
