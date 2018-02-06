/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.opencsv.CSVWriter;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.ApplicationSummary;
import com.portal.user_management.Authentication;
import io.swagger.annotations.Api;
import security.Auth;
import com.portal.commons.models.AuthorizationRole;
import com.portal.commons.models.ManualTransactionView;
import com.portal.commons.models.OnlineTransaction;
import com.portal.commons.models.OnlineTransactionView;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
import com.portal.commons.util.Utility;
import com.portal.payment.PaymentManager;
import com.portal.user_management.AppUserManager;

import javax.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Result;
import static play.mvc.Results.*;
import io.swagger.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang3.RandomStringUtils;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import static play.mvc.Controller.request;
import static play.mvc.Controller.response;
import play.mvc.Http;
import security.AppAuthenticator;

/**
 *
 * @author poseidon
 */
@Transactional
@Api(value = "Payment", tags = {"Payment"})
public class PaymentController extends Controller {

    @Inject
    Authentication authentication;

    @Inject
    FormFactory formFactory;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    PaymentManager paymentManager;

    @Inject
    AppUserManager appUserManager;

    @Inject
    JPAApi aApi;

    @ApiOperation(value = "Create Manual transactions",
            httpMethod = "POST")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Returns an dashboard")
            }
    )
    @BodyParser.Of(value = BodyParser.MultipartFormData.class)
    @Auth(roles = {AuthorizationRole.ADMIN, AuthorizationRole.ACCOUNT})
    public Result processManualTransactions() {
        try {
            Http.MultipartFormData<File> body = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart<File> fileFilePart = body.getFile("manual");
            if (fileFilePart == null) {
                return badRequest("Upload  MultipartFormData with manual filename ");
            }
            AppUser appUser = AppAuthenticator.getAppUser();

            List<Map<String, String>> transactions = Utility.read(fileFilePart.getFile());
            Lists.partition(transactions, 100).parallelStream().forEach((list) -> {
                play.Logger.info("Got [{}] partion for database entry", list.size());
                try {
                    aApi.withTransaction(() -> {
                        list.forEach(f
                                -> {
                            paymentManager.createManualTranscation(appUser.getAppUserId(), f.get("teacher_reg_number"), f.get("bank_name"), f.get("bank_teller"), f.get("amount_paid"), f.get("date_paid"));
                        });
                    });
                } catch (Exception e) {
                    play.Logger.error("Failed to save import", e);
                }

            });

        } catch (Exception e) {

            return internalServerError(e.getMessage());
        }

        return noContent();

    }

    @ApiOperation(value = "Search all online transactions", notes = "",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = com.github.markserrano.jsonquery.jpa.response.JqgridResponse.class)
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "Authorization",
                        dataType = "String",
                        required = true,
                        paramType = "header",
                        value = "Authorization"
                )
            }
    )

    @Auth(roles = {AuthorizationRole.ADMIN, AuthorizationRole.ACCOUNT, AuthorizationRole.OPERATIONS})
    public Result getOnlineTransactions(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord, Boolean export) {

        try {

            JqgridResponse<OnlineTransactionView> applicationListResponse = paymentManager.getOnlineTransactions(search, filters, page, rows, sidx, sord);
            if (export != false && export) {

                String filePath = "exportedCSV/" + RandomStringUtils.randomAlphanumeric(10) + ".csv";

                new File(EnvironMentVariables.STORAGE_PATH + filePath).getParentFile().mkdirs();
                try (CSVWriter writer = new CSVWriter(new FileWriter(EnvironMentVariables.STORAGE_PATH + filePath))) {
                    applicationListResponse.getRows().stream().forEach(a -> {

                        Map<String, Object> map = objectMapper.convertValue(a, new TypeReference<Map<String, Object>>() {
                        });

                        if (applicationListResponse.getRows().indexOf(a) == 0) {
                            Set<String> headerSet = map.keySet();
                            writer.writeNext(headerSet.toArray(new String[headerSet.size()]));
                        }

                        Collection<Object> values = map.values();

                        writer.writeNext(values.stream().map(f -> Objects.toString(f, "")).collect(Collectors.toList()).toArray(new String[values.size()]));

                    });
                }
                response().setHeader("Content-Type", "application/octet-stream");
                response().setHeader("Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord("export.csv") + "\"");
                return ok(new FileInputStream(new File(EnvironMentVariables.STORAGE_PATH + filePath)));
            }
            return ok(objectMapper.writeValueAsString(ImmutableMap.of("data", applicationListResponse))).as("application/json");
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Search all online transactions", notes = "",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = com.github.markserrano.jsonquery.jpa.response.JqgridResponse.class)
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "Authorization",
                        dataType = "String",
                        required = true,
                        paramType = "header",
                        value = "Authorization"
                )
            }
    )

    @Auth(roles = {AuthorizationRole.ADMIN, AuthorizationRole.ACCOUNT, AuthorizationRole.OPERATIONS})
    public Result getManualTransactions(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord, Boolean export) {

        try {

            JqgridResponse<ManualTransactionView> applicationListResponse = paymentManager.getManualTransactions(search, filters, page, rows, sidx, sord);
            if (export != false && export) {

                String filePath = "exportedCSV/" + RandomStringUtils.randomAlphanumeric(10) + ".csv";

                new File(EnvironMentVariables.STORAGE_PATH + filePath).getParentFile().mkdirs();
                try (CSVWriter writer = new CSVWriter(new FileWriter(EnvironMentVariables.STORAGE_PATH + filePath))) {
                    applicationListResponse.getRows().stream().forEach(a -> {

                        Map<String, Object> map = objectMapper.convertValue(a, new TypeReference<Map<String, Object>>() {
                        });

                        if (applicationListResponse.getRows().indexOf(a) == 0) {
                            Set<String> headerSet = map.keySet();
                            writer.writeNext(headerSet.toArray(new String[headerSet.size()]));
                        }

                        Collection<Object> values = map.values();

                        writer.writeNext(values.stream().map(f -> Objects.toString(f, "")).collect(Collectors.toList()).toArray(new String[values.size()]));

                    });
                }
                response().setHeader("Content-Type", "application/octet-stream");
                response().setHeader("Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord("export.csv") + "\"");
                return ok(new FileInputStream(new File(EnvironMentVariables.STORAGE_PATH + filePath)));
            }
            return ok(objectMapper.writeValueAsString(ImmutableMap.of("data", applicationListResponse))).as("application/json");
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Get OnlineTransaction", notes = "",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = OnlineTransaction.class)
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "Authorization",
                        dataType = "String",
                        required = true,
                        paramType = "header",
                        value = "Authorization"
                )
            }
    )

    @Auth
    public Result getOnlineTransaction(String onlineTransactionUd) {

        try {

            return ok(objectMapper.writeValueAsString(paymentManager.getOnlineTransaction(onlineTransactionUd))).as("application/json");
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Retry OnlineTransaction", notes = "",
            httpMethod = "PUT")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = OnlineTransaction.class)
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "Authorization",
                        dataType = "String",
                        required = true,
                        paramType = "header",
                        value = "Authorization"
                )
            }
    )

    @Auth(roles = {AuthorizationRole.ADMIN, AuthorizationRole.ACCOUNT})
    public CompletionStage<Result> retryTransaction(String onlineTransactionUd) throws Exception {
        Date requestTime = new Date();
        final String uri = request().uri();
        return paymentManager.retryTransaction(onlineTransactionUd, true).thenApply(fn -> {
            aApi.withTransaction(() -> {
                try {
                    paymentManager.saveOnlineResponse(uri, objectMapper.writeValueAsString((ObjectNode) fn), requestTime);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(RemittaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return fn;
        }).thenApply(f -> ok(f));

    }

}
