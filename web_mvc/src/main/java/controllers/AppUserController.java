/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import io.swagger.annotations.Api;
import security.Auth;
import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.AuthorizationRole;
import com.portal.commons.util.MyObjectMapper;
import com.portal.user_management.AppUserManager;

import javax.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Result;
import static play.mvc.Results.*;
import io.swagger.annotations.*;
import models.web.ChangePassword;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;

import security.AppAuthenticator;

/**
 *
 * @author poseidon
 */
@Transactional
@Api(value = "User Management", tags = {"User Management"})
public class AppUserController extends Controller {

    @Inject
    AppUserManager appUserManager;

    @Inject
    FormFactory formFactory;

    @Inject
    MyObjectMapper objectMapper;

    @ApiOperation(value = "Change Password",
            httpMethod = "PUT")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = AppUser.class)
                ,
                @ApiResponse(code = 404, message = "AppUser Not Found")
                ,
                @ApiResponse(code = 401, message = "Invalid Details")
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
                ,
                @ApiImplicitParam(
                        name = "body",
                        dataType = "models.web.ChangePassword",
                        required = true,
                        paramType = "body",
                        value = "ChangePassword"
                )
            }
    )
    @Auth(requireAppUserId = true)
    public Result changePassword(String appUserId) {
        Form<ChangePassword> model = formFactory.form(ChangePassword.class).bindFromRequest();
        if (model.hasErrors()) {
            return badRequest(model.errorsAsJson());
        }
        ChangePassword get = model.get();
        try {
            appUserManager.changePassword(AppAuthenticator.getAppUser().getAppUserId(), get.getOldPassword(), get.getNewPassword());
            return noContent();
        } catch (InvalidCredentialsException ex) {
            return unauthorized("Invalid credentials");
        } catch (ResourceNotFound ex) {
            return notFound(ex.getMessage());
        }
    }

    @ApiOperation(value = "Add AppUser", notes = "returs AppUser model",
            httpMethod = "POST")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = AppUser.class)
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
                ,
            @ApiImplicitParam(
                        name = "body",
                        dataType = "com.portal.commons.models.AppUser",
                        required = true,
                        paramType = "body",
                        value = "Form"
                )
            }
    )

    @Auth(roles = AuthorizationRole.ADMIN)
    public Result addAppUser() {

        play.data.Form< AppUser> model = formFactory.form(AppUser.class).bindFromRequest();
        if (model.hasErrors()) {
            return badRequest(model.errorsAsJson());
        }

        try {

            AppUser result = appUserManager.addAppUser(model.get());
            return ok(objectMapper.writeValueAsString(result));
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Edit AppUser", notes = "returs AppUser model",
            httpMethod = "PUT")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done")
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
                ,
            @ApiImplicitParam(
                        name = "body",
                        dataType = "com.portal.commons.models.AppUser",
                        required = true,
                        paramType = "body",
                        value = "Form"
                )
            }
    )

    @Auth(requireAppUserId = true)
    public Result editAppUser(String appUserEmail) {

        play.data.Form< AppUser> model = formFactory.form(AppUser.class).bindFromRequest();
        if (model.hasErrors()) {
            return badRequest(model.errorsAsJson());
        }

        try {

            appUserManager.editAppUser(appUserEmail, model.get());
            return noContent();
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete AppUser", notes = "",
            httpMethod = "DELETE")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = void.class)
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

    @Auth(roles = AuthorizationRole.ADMIN)
    public Result deleteAppUser(String appUserEmail) {

        try {

            appUserManager.deleteAppUser(appUserEmail);

            return noContent();
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Search all AppUser", notes = "",
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

    @Auth(roles = AuthorizationRole.ADMIN)
    public Result getAppUsers(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord) {

        try {

            JqgridResponse<AppUser> result = null;
            result = appUserManager.getAppUsers(search, filters, page, rows, sidx, sord);
            return ok(objectMapper.writeValueAsString(result)).as("application/json");
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

}
