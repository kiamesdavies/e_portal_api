/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.exceptions.ResourceAlreadtExist;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.LoginModel;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
import com.portal.user_management.Authentication;

import javax.inject.Inject;

import models.web.Helper;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import static play.mvc.Results.*;
import io.swagger.annotations.*;
import models.web.FinalizePasswordRecovery;

import play.Logger;

import play.data.Form;
import play.data.FormFactory;

/**
 *
 * @author poseidon
 */
@Transactional
@Api(value = "User Management", tags = {"User Management"})
public class AuthenticationController extends Controller {

    @Inject
    Authentication authentication;

    @Inject
    FormFactory formFactory;

    @Inject
    MyObjectMapper objectMapper;

    @ApiOperation(value = "Get Availabilty of Username",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 203, message = "Username is available", response = Void.class)
                ,
                @ApiResponse(code = 400, message = "Username not available", response = Void.class),}
    )

    public Result isUsernameAvailable(String username) {
        return authentication.isUserNameAvailable(username) ? ok() : badRequest();
    }

    @ApiOperation(value = "Log in to an account using enkentoUrl", notes = "It authomatically log u to an organization if need be",
            httpMethod = "POST")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Successfully log in")
                ,
                @ApiResponse(code = 401, message = "Login Failed")
                ,
                @ApiResponse(code = 400, message = "Bad Request.")
                ,
                @ApiResponse(code = 500, message = "System failed ")}
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "body",
                        dataType = "com.portal.commons.models.LoginModel",
                        required = true,
                        paramType = "body",
                        value = "LoginModel"
                )
            }
    )
    public Result enkentoLogin(String returnUrl) {
        Form<LoginModel> bindFromRequest = formFactory.form(LoginModel.class).bindFromRequest();
        if (bindFromRequest.hasErrors()) {
            return badRequest(bindFromRequest.errorsAsJson());
        }
        return login(bindFromRequest.get().getUsername(), bindFromRequest.get().getPassword());

    }

    @ApiOperation(value = "Finalize Password Recovery",
            httpMethod = "PUT")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Password reset complete")
                ,
                @ApiResponse(code = 400, message = "Failed to rest password")
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "body",
                        dataType = "models.web.FinalizePasswordRecovery",
                        required = true,
                        paramType = "body",
                        value = "FinalizePasswordRecovery"
                )
            }
    )
    public Result finalizePasswordRecovery() {
        Form<FinalizePasswordRecovery> bindFromRequest = formFactory.form(FinalizePasswordRecovery.class).bindFromRequest();
        if (bindFromRequest.hasErrors()) {
            return badRequest(bindFromRequest.errorsAsJson());
        }
        FinalizePasswordRecovery get = bindFromRequest.get();
        try {
            authentication.finalizePasswordRecovery(get.getToken(), get.getPassword());
            return noContent();
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }
    }

    @ApiOperation(value = "Password Recovery",
            notes = "Password Recovery",
            httpMethod = "POST")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Queue password recovery emailed and returned password token")
                ,
                @ApiResponse(code = 400, message = "Email not found")
            }
    )
    public Result passwordRecovery(String appUserEmail) {
        try {
            authentication.startPasswordRecovery(appUserEmail);
            return noContent();
        } catch (Exception ex) {
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Login into the system throug a mobile device", nickname = "Login", notes = "Returns token as a json object", httpMethod = "GET", responseHeaders = {
        @ResponseHeader(name = "Authorization")})
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Successfully log in")
                ,
                @ApiResponse(code = 401, message = "Login Failed")
                ,
                @ApiResponse(code = 400, message = "Bad Request.")
                ,
                @ApiResponse(code = 412, message = "Email Verification Required")
                ,
                @ApiResponse(code = 500, message = "System failed ")}
    )
    private Result login(String username, String password) {
        try {

            String login = authentication.login(username, password, true);

            AppUser appUser = authentication.getAppUser(username);
            appUser.setPassword(null);
            appUser.setToken(login);

            response().setCookie(
                    "token", // name
                    login, // value
                    null, // maximum age
                    "/", // path
                    EnvironMentVariables.COOKIE_SERVER_URL, // domain
                    false, // secure
                    true // http only
            );
            
            response().setCookie(
                    "token", // name
                    login, // value
                    null, // maximum age
                    "/", // path
                    EnvironMentVariables.APP_SERVER_URL, // domain
                    false, // secure
                    true // http only
            );
            response().setCookie(
                    "token", // name
                    login, // value
                    null, // maximum age
                    "/", // path
                    EnvironMentVariables.ENKETO_SERVER_URL, // domain
                    false, // secure
                    true // http only
            );
            return ok(this.objectMapper.writeValueAsString(ImmutableMap.of("appUser", appUser))).withHeader("Authorization", login);
        } catch (VerifyError ex) {
            return status(412, "Email Verification Required");
        } catch (InvalidCredentialsException ex) {
            return unauthorized("Login Failed");
        } catch (RuntimeException ex) {
            Logger.error("Error", ex);
            return badRequest(String.format("Something went wrong. Reason: %s ", ex.getMessage()));
        } catch (Exception ex) {
            Logger.error("Error", ex);
            return internalServerError("Something went wrong. The error has been logged fpr further processing");
        }
    }

    @ApiOperation(value = "Login into the system", nickname = "Login", notes = "Returns token as a json object", httpMethod = "GET", responseHeaders = {
        @ResponseHeader(name = "Authorization")})
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Successfully log in")
                ,
                @ApiResponse(code = 401, message = "Login Failed")
                ,
                @ApiResponse(code = 400, message = "Bad Request.")
                ,
                @ApiResponse(code = 412, message = "Email verification required.")
                ,
                @ApiResponse(code = 500, message = "System failed ")}
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "body",
                        dataType = "com.portal.commons.models.LoginModel",
                        required = true,
                        paramType = "body",
                        value = "LoginModel"
                )
            }
    )
    public Result loginWithModel() {
        Form<LoginModel> bindFromRequest = formFactory.form(LoginModel.class).bindFromRequest();
        if (bindFromRequest.hasErrors()) {
            return badRequest(bindFromRequest.errorsAsJson());
        }
        return login(bindFromRequest.get().getUsername(), bindFromRequest.get().getPassword());
    }

    @ApiOperation(value = "Sign Up",
            nickname = "Sign Up",
            notes = "Register the user and returns a token as a json object.",
            httpMethod = "POST", responseHeaders = {
                @ResponseHeader(name = "Authorization")})
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Successfully sign up")
                ,
                @ApiResponse(code = 401, message = "Login Failed after sign up")
                ,
                @ApiResponse(code = 409, message = "User already exists")
                ,
                @ApiResponse(code = 400, message = "Bad Request.")
                ,
                @ApiResponse(code = 500, message = "System failed ")
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "body",
                        dataType = "com.portal.commons.models.AppUser",
                        required = true,
                        paramType = "body",
                        value = "AppUser"
                )
            }
    )
    public Result signUp() {
        Form<AppUser> bindFromRequest = formFactory.form(AppUser.class).bindFromRequest();
        if (bindFromRequest.hasErrors()) {
            return badRequest(bindFromRequest.errorsAsJson());
        }
        try {

            AppUser requestAppUser = bindFromRequest.get();
//            try {
//
//                JSONObject result = webb.get("http://freegeoip.net/json/" + request().remoteAddress()).ensureSuccess().asJsonObject().getBody();
//                requestAppUser.setCountry(result.getString("country_name"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            AppUser appUser = authentication.signUp(requestAppUser);
            this.createDefault(appUser);

            return login(appUser.getUserName(), appUser.getPassword());
            //return ok(authTokenJson).withHeader("Authorization", token);
        } catch (ResourceAlreadtExist ex) {
            return status(409, "User Already Exists");
        } //        catch (InvalidCredentialsException ex) {
        //            return unauthorized("Failed to sign up");
        //        }
        catch (RuntimeException ex) {
            Logger.error("Error", ex);
            return badRequest(String.format("Something went wrong. Reason: %s ", ex.getMessage()));
        } catch (Exception ex) {
            Logger.error("Error", ex);
            return internalServerError("Something went wrong. The error has been logged fpr further processing");
        }
    }

    private void createDefault(AppUser appUser) {

    }

}
