/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.google.common.collect.ImmutableMap;
import com.opencsv.CSVWriter;
import io.swagger.annotations.Api;
import security.Auth;
import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.AuthorizationRole;
import com.portal.commons.models.FormVersion;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
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
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.mail.internet.MimeUtility;
import models.web.ChangePassword;
import org.apache.commons.lang3.RandomStringUtils;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import static play.mvc.Controller.response;

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
    public Result getAppUsers(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord, Boolean export) {

        try {

            JqgridResponse<AppUser> applicationListResponse = appUserManager.getAppUsers(search, filters, page, rows, sidx, sord);
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

}
