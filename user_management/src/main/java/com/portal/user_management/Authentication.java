/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.user_management;

import akka.actor.ActorSystem;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.github.markserrano.jsonquery.jpa.enumeration.OrderEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.github.markserrano.jsonquery.jpa.service.IFilterService;
import com.github.markserrano.jsonquery.jpa.specifier.Order;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;
import com.google.common.collect.ImmutableMap;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.OrderSpecifier;
import com.portal.commons.models.AppUser;
import com.portal.commons.events.Sms;
import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.exceptions.ResourceAlreadtExist;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.GeneralMapper;
import com.portal.commons.models.SmsLog;
import com.portal.commons.util.CycleAvoidingMappingContext;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
import com.portal.configuration.IConfiguration;
import com.portal.configuration.IConfiguration.Config;
import com.portal.configuration.IMessageTemplate;
import com.portal.entities.JpaAppUser;
import com.portal.entities.JpaPinRequest;
import com.portal.entities.JpaSmsLog;
import com.portal.entities.QJpaPinRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import javax.inject.Inject;
import mock.springframework.data.domain.Page;
import mock.springframework.data.domain.PageRequest;
import mock.springframework.data.domain.Pageable;
import org.apache.commons.lang3.RandomStringUtils;
import org.javatuples.Pair;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import play.api.libs.ws.WSClient;
import play.db.jpa.JPAApi;
import scala.concurrent.duration.Duration;

/**
 *
 * @author poseidon
 */
public class Authentication {

    private static final Logger logger = LoggerFactory.getLogger(Authentication.class);
    @Inject
    private ActorSystem actorSystem;

    @Inject
    JPAApi jPAApi;

    @Inject
    JpaAppUserRepository appUserRepository;

    @Inject
    private IMessageTemplate messageTemplate;

    @Inject
    WSClient wSClient;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    private IConfiguration configuration;

    @Inject
    IFilterService filterService;

    private static JWTSigner signer;
    private static JWTVerifier verifier;

    public String generateToken() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("username", "Anonymous");
        userMap.put("roleId", "none");
        return getSigner().sign(userMap, new JWTSigner.Options().setIssuedAt(true).setExpirySeconds(3600));
    }

    public JqgridResponse<SmsLog> getSMsLog(Boolean search, String filters,
            Integer page, Integer rows, String sidx, String sord) {
        filters = filters == null || filters == "" || !search ? QueryUtil.INIT_FILTER : filters;
        Order order = new Order(JpaSmsLog.class);
        OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
        JsonFilter jsonFilter = new JsonFilter(filters);
        BooleanBuilder build = filterService.getJsonBooleanBuilder(JpaSmsLog.class).build(jsonFilter);
        if (rows == null) {
            rows = filterService.count(build, JpaSmsLog.class, orderSpecifier).intValue();
        }
        Pageable pageable = new PageRequest(page >= 1 ? page - 1 : 0, rows > 0 ? rows : 1);
        Page<JpaSmsLog> results = filterService.readAndCount(build, pageable, JpaSmsLog.class, orderSpecifier);
        JqgridResponse<SmsLog> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(GeneralMapper.INSTANCE::jpaSmsLogToSmsLog).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }

    public String login(String username, String password, boolean expire) throws InvalidCredentialsException, VerifyError {

        logger.debug("Login for user with username {}, password {}", username, password);
        try {
            JpaAppUser jpaAppUser = appUserRepository.getJpaAppUser(username);

            if (PasswordHash.validatePassword(password, jpaAppUser.getPassword())) {
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("username", username);
                userMap.put("roleId", jpaAppUser.getRoleName());
                logger.debug("Making token for username {}  ", username);
                String result = getSigner().sign(userMap, new JWTSigner.Options().setIssuedAt(true).setExpirySeconds(!expire ? Integer.MAX_VALUE : Integer.parseInt(configuration.getConfiguration(Config.TOKEN_EXPIRY_SECONDS))));
                logger.debug("Done making token  of  username {}, token is {}", username, result);
                return result;
            }

        } catch (ResourceNotFound | NoSuchAlgorithmException | InvalidKeySpecException ex) {

        }
        throw new InvalidCredentialsException("");
    }

    public String signMap(Map<String, Object> userMap, boolean expire) throws ResourceNotFound {
        return getSigner().sign(userMap, new JWTSigner.Options().setIssuedAt(true).setExpirySeconds(!expire ? Integer.MAX_VALUE : Integer.parseInt(configuration.getConfiguration(Config.TOKEN_EXPIRY_SECONDS))));
    }

    public AppUser signUp(AppUser appUser) throws ResourceAlreadtExist {

        if (!this.isUserNameAvailable(appUser.getUserName())) {
            throw new ResourceAlreadtExist(String.format("User with username %s already exists", appUser.getUserName()));
        }
        String realPassword = appUser.getPassword();
        try {
            appUser.password(PasswordHash.createHash(appUser.getPassword()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            logger.error("System failed to genetate hash for username {} and password", appUser.getUserName(), appUser.getPassword());
            throw new RuntimeException("System failed to generate password");
        }
        appUser.setAppUserId(java.util.UUID.randomUUID().toString());
        appUser.setDateCreated(new Date());
        appUser.createdBy("Sign Up");
        appUser.setActive(Boolean.TRUE);
        appUser.setRegistrationDate(new Date());
        try {
            appUser.roleName(configuration.getConfiguration(Config.DEFAULT_ROLE));
        } catch (ResourceNotFound ex) {
            throw new RuntimeException("Configuration default role not found");
        }

        jPAApi.em().persist(GeneralMapper.INSTANCE.generatedAppUserToJpaAppUser(appUser, new CycleAvoidingMappingContext()));
        jPAApi.em().flush();
        appUser.password(realPassword);

        return appUser;

    }

    public AppUser getAppUser(String appUserEmail) throws ResourceNotFound {
        return GeneralMapper.INSTANCE.jpaAppUserToGeneratedAppUser(appUserRepository.getJpaAppUser(appUserEmail), new CycleAvoidingMappingContext()
        );
    }

    public Pair<AppUser, String> tokenIsValid(String token) throws InvalidCredentialsException {
        logger.debug("Validating token {}", token);
        try {

            Map<String, Object> verifyToken = getVerifier().verify(token);

            return Pair.with(GeneralMapper.INSTANCE.jpaAppUserToGeneratedAppUser(appUserRepository.getJpaAppUser(verifyToken.get("username").toString()), new CycleAvoidingMappingContext()), verifyToken.get("roleId").toString());

        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalStateException | IOException | SignatureException | JWTVerifyException ex) {
            throw new InvalidCredentialsException(token);
        } catch (ResourceNotFound ex) {
            throw new InvalidCredentialsException(ex.getMessage());
        }

    }

    public void startPasswordRecovery(String appUserId) throws ResourceNotFound {
        String pin = RandomStringUtils.randomNumeric(8);
        JpaAppUser jpaAppUser = appUserRepository.getJpaAppUser(appUserId);
        String name = AppUser.getJpaName(jpaAppUser);

        String msgTemplate = messageTemplate.getMessageTemplate(IMessageTemplate.MessageType.PIN_RESET).getSmsTemplate();
        String pinExpirySeconds = configuration.getConfiguration(IConfiguration.Config.PIN_EXPIRY_SECONDS);
        Date now = new Date();
        DateTime dateTime = new DateTime(now);
        Date expiryDate = dateTime.plusSeconds(Integer.valueOf(pinExpirySeconds)).toDate();
        JpaPinRequest jpaPinRequest = new JpaPinRequest(pin, false, now);
        jpaPinRequest.setAppUserId(jpaAppUser);

        jPAApi.em().persist(jpaPinRequest);

        String body = messageTemplate.parseMessageTemplate(msgTemplate, ImmutableMap.of("expiryDate", new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(expiryDate), "pin", pin, "name", name));
        Sms sms = new Sms(jpaAppUser.getMobileNumber(), "TRCN", body);
        actorSystem.scheduler().scheduleOnce(Duration.create(1, TimeUnit.SECONDS),
                () -> actorSystem.eventStream().publish(sms), actorSystem.dispatcher());
    }

    public void finalizePasswordRecovery(String pin, String newPassword) throws ResourceNotFound, IllegalStateException, NoSuchAlgorithmException, InvalidKeySpecException {
        QJpaPinRequest qjpr = QJpaPinRequest.jpaPinRequest;
        JpaPinRequest fetchOne = new JPAQueryFactory(jPAApi.em()).selectFrom(QJpaPinRequest.jpaPinRequest).where(qjpr.pinId.equalsIgnoreCase(pin), QJpaPinRequest.jpaPinRequest.used.isFalse()).fetchOne();
        if (fetchOne == null) {
            throw new ResourceNotFound(String.format("No pin request %s is available", pin));
        }
        String pinExpirySeconds = configuration.getConfiguration(IConfiguration.Config.PIN_EXPIRY_SECONDS);
        Date now = new Date();
        DateTime dateTime = new DateTime(now);
        Date expiryDate = dateTime.plusSeconds(Integer.valueOf(pinExpirySeconds)).toDate();
        if (now.after(expiryDate)) {
            throw new IllegalStateException(String.format("Pin %s expired at %s", pin, new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(expiryDate)));
        }
        fetchOne.setUsed(true);
        fetchOne.setDateUsed(now);

        JpaAppUser jpaAppUser = fetchOne.getAppUserId();
        jpaAppUser.setPassword(PasswordHash.createHash(newPassword));
        jpaAppUser.setDateModified(new Date());
        jpaAppUser.setModifiedBy("Password Recovery");

        jPAApi.em().merge(fetchOne);
        jPAApi.em().merge(jpaAppUser);
    }

    /**
     * return true if the email can be used for registration
     *
     * @return
     */
    public boolean isUserNameAvailable(String username) {
        try {
            appUserRepository.getJpaAppUser(username);

            return false;
        } catch (ResourceNotFound ex) {

        }

        return true;
    }

    public JWTSigner getSigner() {
        if (signer == null) {
            signer = new JWTSigner(EnvironMentVariables.AUTHENTICATION_TOKEN);

        }
        return signer;
    }

    public JWTVerifier getVerifier() {
        if (verifier == null) {
            verifier = new JWTVerifier(EnvironMentVariables.AUTHENTICATION_TOKEN);
        }
        return verifier;
    }

}
