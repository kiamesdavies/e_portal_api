/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portal.commons.util.MyObjectMapper;
import com.portal.user_management.Authentication;
import javax.inject.Inject;
import play.data.FormFactory;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import io.swagger.annotations.Api;
import security.Auth;
import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.AuthorizationRole;
import com.portal.commons.models.OnlineTransaction;
import com.portal.commons.models.Payment;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
import com.portal.payment.PaymentManager;
import com.portal.user_management.AppUserManager;

import javax.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Result;
import static play.mvc.Results.*;
import io.swagger.annotations.*;
import java.util.Date;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.web.ChangePassword;

import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.Controller;
import static play.mvc.Controller.request;

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
    public Result getOnlineTransactions(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord) {

        try {

            return ok(objectMapper.writeValueAsString(paymentManager.getOnlineTransactions(search, filters, page, rows, sidx, sord))).as("application/json");
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
    public Result getManualTransactions(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord) {

        try {

            return ok(objectMapper.writeValueAsString(paymentManager.getManualTransactions(search, filters, page, rows, sidx, sord))).as("application/json");
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
