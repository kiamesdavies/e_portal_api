/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.OnlineTransaction;
import com.portal.commons.models.Payment;
import io.swagger.annotations.Api;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
import com.portal.payment.PaymentManager;
import com.portal.payment.ResponseObject;
import com.portal.user_management.AppUserManager;
import com.portal.user_management.Authentication;

import javax.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Result;
import static play.mvc.Results.*;
import io.swagger.annotations.*;
import java.util.Date;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.Controller;

/**
 *
 * @author poseidon
 */
@Transactional
@Api(value = "Remitta", tags = {"Remitta"})
public class RemittaController extends Controller {

    @Inject
    Authentication authentication;

    @Inject
    FormFactory formFactory;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    PaymentManager paymentManager;

    @Inject
    JPAApi aApi;

    @Inject
    AppUserManager appUserManager;

    @ApiOperation(value = "Initialize payment", notes = "",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = com.github.markserrano.jsonquery.jpa.response.JqgridResponse.class)
            }
    )

    public Result initializePayment(String appUserId) throws Exception {
        try {
            AppUser appUser = appUserManager.getAppUser(appUserId);
            Payment initializePayment = paymentManager.initializePayment(appUserId, "online", "remitta", null);
            OnlineTransaction onlineTransaction = paymentManager.billPayment(initializePayment.getPaymentId());

            String responseUrl = String.format(EnvironMentVariables.REMITTA_RESPONSE_URL, onlineTransaction.getTransactionId());
            //hash format merchantId+serviceTypeId+orderId+amount+responseurl+api_key
            String hash = PaymentManager.generateHashedStringSHA512(EnvironMentVariables.REMITTA_MERCHANTID + EnvironMentVariables.REMITTA_SERVICETYPEID + onlineTransaction.getTransactionId() + onlineTransaction.getAmountToPay() + responseUrl + EnvironMentVariables.REMITTA_APIKEY);

            return ok(String.format(PAYMENT_HTML, EnvironMentVariables.REMITTA_MERCHANTID, EnvironMentVariables.REMITTA_SERVICETYPEID, onlineTransaction.getTransactionId(), hash, appUser.getName(), appUser.getMobileNumber(), onlineTransaction.getAmountToPay() + "", responseUrl)).as("text/html");
        } catch (ResourceNotFound ex) {
            return notFound(ex.getMessage());
        }
    }

    @ApiOperation(value = "Remitta Single Callback ", httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = Void.class)
            }
    )
    public CompletionStage<Result> completeInstantOnlineTransaction(String rrr, String orderId) throws Exception {

        Date requestTime = new Date();
        final String uri = request().uri();
        CompletionStage<JsonNode> event = paymentManager.completeInstantOnlineTransaction(rrr, false).thenApply(fn -> {
            aApi.withTransaction(() -> {
                try {
                    paymentManager.saveOnlineResponse(uri, objectMapper.writeValueAsString((ObjectNode) fn), requestTime);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(RemittaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return fn;
        });

        return event.thenApply(i -> redirect(String.format("%s/main.html#payment/%s", EnvironMentVariables.APP_SERVER_URL, orderId)));

    }

    @ApiOperation(value = "Remitta batch", httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = Void.class)
            }
    )
    public Result remittaBatch() throws JsonProcessingException, Exception {
        JsonNode batch = request().body().asJson();

        Date requestTime = new Date();
        final String uri = request().uri();
        paymentManager.saveOnlineResponse(uri, objectMapper.writeValueAsString(batch), requestTime);
        if (batch.isArray()) {
            for (final JsonNode objNode : batch) {
                ResponseObject responseObject = objectMapper.treeToValue(objNode, ResponseObject.class);
                paymentManager.completeBatchOnlineTransaction(responseObject).thenApply(fn -> {
                    aApi.withTransaction(() -> {
                        try {
                            paymentManager.saveOnlineResponse(uri, objectMapper.writeValueAsString((ObjectNode) fn), requestTime);
                        } catch (JsonProcessingException ex) {
                            Logger.getLogger(RemittaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    return fn;
                });
            }
        } else {
            ResponseObject responseObject = objectMapper.treeToValue(batch, ResponseObject.class);
            paymentManager.completeBatchOnlineTransaction(responseObject).thenApply(fn -> {
                aApi.withTransaction(() -> {
                    try {
                        paymentManager.saveOnlineResponse(uri, objectMapper.writeValueAsString((ObjectNode) fn), requestTime);
                    } catch (JsonProcessingException ex) {
                        Logger.getLogger(RemittaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                return fn;
            });
        }
        return noContent();
    }

    private final static String PAYMENT_HTML = "<html>\n"
            + "      <body>\n"
            + "            <form style=\"display:none\" action=\"http://www.remitademo.net/remita/ecomm/init.reg\" name=\"SubmitRemitaForm\" method=\"POST\">\n"
            + "                  <input name=\"merchantId\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input name=\"serviceTypeId\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input name=\"orderId\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input name=\"hash\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input name=\"payerName\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input name=\"payerEmail\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input name=\"amt\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input name=\"responseurl\" value=\"%s\" type=\"hidden\">\n"
            + "                  <input type =\"submit\" name=\"submit_btn\" >\n"
            + "           </form>\n"
            + "<script type=\"text/javascript\">document.SubmitRemitaForm.submit(); </script>  "
            + "      </body>\n"
            + "</html>";

}
