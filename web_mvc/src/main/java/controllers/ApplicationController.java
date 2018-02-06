/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import akka.actor.ActorSystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.goebl.david.Webb;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.opencsv.CSVWriter;
import com.portal.applications.ApplicationManager;
import io.swagger.annotations.Api;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.ApplicationData;
import com.portal.commons.models.ApplicationSummary;
import com.portal.commons.models.AuthorizationRole;
import com.portal.commons.models.FormVersion;
import com.portal.commons.models.Payment;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.commons.util.MyObjectMapper;
import com.portal.commons.util.Utility;
import com.portal.payment.PaymentManager;
import com.portal.user_management.AppUserManager;

import javax.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Result;
import static play.mvc.Results.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import models.web.Helper;
import org.apache.commons.lang3.RandomStringUtils;

import org.javatuples.Pair;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.*;
import security.Auth;

import io.swagger.annotations.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.Play;
import security.AppAuthenticator;

/**
 *
 * @author poseidon
 */
@Transactional
@Api(value = "Application", tags = {"Application"})
public class ApplicationController extends Controller {

    @Inject
    AppUserManager appUserManager;

    @Inject
    FormFactory formFactory;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    ApplicationManager applicationManager;

    @Inject
    private ActorSystem actorSystem;

    @Inject
    private WSClient wSClient;

    @Inject
    PaymentManager paymentManager;

    @Inject
    private Webb webb;

    @Inject
    private Helper helper;

    
    

    private static final String SEPERATOR = "---";

    private static final Function<String, String> GET_REAL_FORMVERSIONID = (formVersionId) -> {
        if (formVersionId.contains(SEPERATOR)) {
            return formVersionId.split(SEPERATOR)[1];
        }
        return formVersionId;
    };
    private static final Function<String, Pair<String, String>> GET_APPUSERID_AND_FORMVERSIONID = (formVersionId) -> {
        String[] split = formVersionId.split(SEPERATOR);
        return Pair.with(split[0], split[1]);
    };

    @ApiOperation(value = "Get Manual template",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Returns an inputstream", response = FileInputStream.class)
            }
    )
    public Result getManualTemplate() throws UnsupportedEncodingException {
        response().setHeader("Content-Type", "application/octet-stream");
        response().setHeader("Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord("template.csv") + "\"");

        return ok(Play.application().resourceAsStream("template.csv"));

    }

    @ApiOperation(value = "Get Dashboard",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Returns an dashboard")
            }
    )
    public Result getDashboard() throws JsonProcessingException {
        return ok(objectMapper.writeValueAsString(applicationManager.getDashboard()));

    }

    @ApiOperation(value = "Get certificate",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Returns an inputstream", response = FileInputStream.class)
            }
    )
    public Result getCertificate(String appUserId) {
        try {

            String certificate = paymentManager.getCertificate(appUserId);
            if (certificate == null) {
                return notFound("Certificate not found");
            }
            response().setHeader("Content-Type", "application/octet-stream");
            response().setHeader("Content-Disposition", "attachment; filename=\"" + MimeUtility.encodeWord("certificate.pdf") + "\"");

            return ok(new FileInputStream(new File(certificate)));
        } catch (ResourceNotFound | FileNotFoundException | UnsupportedEncodingException e) {
            return badRequest(e.getMessage());
        }

    }

    @ApiOperation(value = "Search all applications", notes = "",
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
    public Result getApplications(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord, Boolean export) {

        try {

            JqgridResponse<ApplicationSummary> applicationListResponse = applicationManager.getApplications(search, filters, page, rows, sidx, sord);
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

    @ApiOperation(value = "Search all form versions", notes = "",
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

    @Auth(roles = {AuthorizationRole.ADMIN})
    public Result getFormVersions(Boolean search, String filters, Integer page, Integer rows, String sidx, String sord, Boolean export) {

        try {

            JqgridResponse<FormVersion> applicationListResponse = applicationManager.getFormVersions(search, filters, page, rows, sidx, sord);
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

    @ApiOperation(value = "Delete application", notes = "",
            httpMethod = "DELETE")
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
            }
    )

    @Auth(roles = AuthorizationRole.ADMIN)
    public Result deleteFormVersion(String formVersionId) {

        try {
            applicationManager.deleteFormVersion(formVersionId);
            return noContent();
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Get application", notes = "",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = ApplicationSummary.class)
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
    public Result getApplicationSummary(String appUserId) {

        try {
            return ok(objectMapper.writeValueAsString(applicationManager.getApplicationSummary(appUserId)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(ex.getMessage());
        }
    }

    @ApiOperation(value = "Add new form version, you will need to  upload a valid xlsform form",
            httpMethod = "POST")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = FormVersion.class)
                ,
                @ApiResponse(code = 404, message = "Not Found")
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
                        //dataType = "play.mvc.Http.MultipartFormData",
                        required = true,
                        paramType = "body",
                        value = "file"
                )
            }
    )
    @BodyParser.Of(value = BodyParser.MultipartFormData.class)
    @Auth(roles = AuthorizationRole.ADMIN)
    public Result addFormVersion(String formId) {
        try {
            Http.MultipartFormData<File> body = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart<File> fileFilePart = body.getFile("xlsform");
            if (fileFilePart == null) {
                return badRequest("Upload  MultipartFormData with xlsform filename ");
            }
            File file = fileFilePart.getFile();

            return this.addFormVersionFunction(file, formId);

        } catch (ResourceNotFound resourceNotFound) {
            return notFound(resourceNotFound.getMessage());
        } catch (Exception e) {

            return internalServerError(e.getMessage());
        }

    }

    private Result addFormVersionFunction(File file, String formId) throws IOException, ResourceNotFound {
        final String formVersionId = RandomStringUtils.randomAlphanumeric(16);

        List<Pair<String, String>> completeFuture = helper.convertXlsForm(file, formVersionId);

        Optional<Result> findFirst = completeFuture.parallelStream().map(r -> {
            if (r != null && r.getValue0() == "error") {
                return badRequest(r.getValue1());
            }
            return null;
        }).filter(g -> g != null).findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get();
        }
        String xformResponseJson = completeFuture.get(0).getValue0();
        String xformResponse = completeFuture.get(0).getValue1();
        String jsonResponse = completeFuture.get(1).getValue1();
        String previewUrl = completeFuture.get(2).getValue1();
        JsonNode jsonNode = objectMapper.readValue(xformResponseJson, JsonNode.class);
        if (!(jsonNode.get("message").asText().contains("Ok") || jsonNode.get("message").asText().contains("OK"))) {
            return badRequest(jsonNode.get("message").asText());
        }

        FormVersion formVersion = applicationManager.addFormVersion(formId, formVersionId, jsonResponse, xformResponse, previewUrl);

        return ok(objectMapper.writeValueAsString(formVersion));
    }

    @ApiOperation(value = "A static url that can be used to capture form at any  time",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = String.class)
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "formVersionId", dataType = "String", required = false, paramType = "query", value = "If empty, latest form version id will be used")
                ,
                    @ApiImplicitParam(
                        name = "returnUrl", dataType = "String", required = false, paramType = "query", value = "Where the page should return to after capturing data")
                ,
                    @ApiImplicitParam(
                        name = "single", dataType = "String", required = false, paramType = "query", value = "Capture a single record and return to returnUrl. ")
            }
    )
    @Auth
    public Result staticUrl(String formId, String returnUrl, Boolean single, String appUserId) throws JSONException, ResourceNotFound {

        AppUser appUser = AppAuthenticator.getAppUser();

        String formVersionId = applicationManager.getLastestFormVersion(formId).getFormVersionId();

        if (single) {
            if (appUser.getRoleName().equalsIgnoreCase("TEACHER")) {
                formVersionId = String.format("%s%s%s", appUser.getAppUserId(), SEPERATOR, formVersionId);
            } else if (StringUtils.isBlank(appUserId)) {
                return badRequest("No appUserId");
            } else {
                formVersionId = String.format("%s%s%s", appUserId, SEPERATOR, formVersionId);
            }
        }

        JSONObject result = webb.post(Helper.ENKETO_SEVER_URL + "/api/v2/" + (single ? "survey/single/once" : "survey/offline"))
                .header("Authorization", EnvironMentVariables.ENKETO_SERVER_TOKEN)
                .param("server_url", Helper.API_SEVER_URL)
                .param("form_id", formVersionId)
                .param("return_url", returnUrl).ensureSuccess().asJsonObject().getBody();

        return redirect((single ? result.get("single_once_url").toString() : result.get("offline_url").toString()).replaceAll("https:", "http:"));
    }

//    @ApiOperation(value = "Return offline link to capture data",
//            httpMethod = "GET")
//    @ApiResponses(
//            value = {
//                @ApiResponse(code = 200, message = "Done", response = String.class)
//            }
//    )
//    @Auth
//    public Result webForm(String formId) throws JSONException, ResourceNotFound {
//
//        String formVersionId = applicationManager.getLastestFormVersion(formId).getFormVersionId();
//        JSONObject result = webb.post(Helper.ENKETO_SEVER_URL + "/api/v2/survey/offline").header("Authorization", EnvironMentVariables.ENKETO_SERVER_TOKEN).param("server_url", Helper.API_SEVER_URL).param("form_id", formVersionId).ensureSuccess().asJsonObject().getBody();
//
//        return ok(result.get("offline_url").toString().replaceAll("https:", "http:"));
//    }
//
//    @ApiOperation(value = "Return offline link to edit form data ",
//            httpMethod = "GET")
//    @ApiResponses(
//            value = {
//                @ApiResponse(code = 200, message = "Done", response = String.class)
//            }
//    )
    public Result webFormForEdit(String formId, String formVersionId, String formDataId, String returnUrl) throws IOException, JSONException {
        try {

            FormVersion formVersion = applicationManager.getFormVersion(GET_REAL_FORMVERSIONID.apply(formVersionId));

            ApplicationData applicationData = applicationManager.getApplicationData(formDataId);
            JsonNode jsonStructure = objectMapper.readValue(formVersion.getJsonStructure(), JsonNode.class);

            JsonNode jsonNode = objectMapper.readValue(applicationData.getFormData(), JsonNode.class);
            String instanceId = null;

            if (jsonNode.has("meta") && jsonNode.get("meta").has("instanceID") && jsonNode.get("meta").get("instanceID").asText() != null && jsonStructure.has("name")) {
                instanceId = jsonNode.get("meta").get("instanceID").asText();
            } else {
                return notFound("Instance Id or name in json structure not found or null");
            }

            String xml = applicationData.getXmlFormData();
            if (xml == null) {
                return badRequest("Form Data has no xml form data");
            }

            JSONObject result = webb.post(Helper.ENKETO_SEVER_URL + "/api/v2/instance").header("Authorization", "Basic RUVSVElVQ0pTSERHS0hEMjM0MzI1Og==").param("server_url", Helper.API_SEVER_URL).param("instance", xml).param("instance_id", instanceId.replaceAll("uuid:", "")).param("form_id", formVersionId).param("return_url", StringUtils.isEmpty(returnUrl) ? Helper.APP_SEVER_URL : returnUrl).ensureSuccess().asJsonObject().getBody();

            return ok(result.get("edit_url").toString());
        } catch (ResourceNotFound ex) {

            return notFound(ex.getMessage());
        }
    }

    @ApiOperation(value = "Returns the value of the X-OpenRosa-Content-Length header returned by the OpenRosa server for this form. Returns 10 by default",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done")
            }
    )
    public Result submissionMaxSize(String formIdVersionId) {
        response().setHeader("x-openrosa-accept-content-length", "10000000000000000");
        return noContent();

    }

    @ApiOperation(value = "Download an xform of the specified formVersionId",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done")
            }
    )
    public Result downloadXform(String formVersionId) {

        try {
            System.out.println("Replace with " + formVersionId);
            return ok(applicationManager.getFormVersion(GET_REAL_FORMVERSIONID.apply(formVersionId)).getXformStructure().replaceAll(GET_REAL_FORMVERSIONID.apply(formVersionId), formVersionId)).as("application/xml");
        } catch (ResourceNotFound resourceNotFound) {
            return notFound(resourceNotFound.getMessage());
        }

    }

    @ApiOperation(value = "Get the details of the specified formVersion ",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done")
            }
    )

    public Result formlist(String formIdVersionId) throws NoSuchAlgorithmException {

        try {

            String downloadUrl;
            String md5;
            String name;
            String descriptionText;

            FormVersion formVersion = applicationManager.getFormVersion(GET_REAL_FORMVERSIONID.apply(formIdVersionId));
            downloadUrl = String.format("%s/form_versions/%s/download.xform", Helper.API_SEVER_URL, formIdVersionId);
            md5 = Utility.generateMD5(formVersion.getXformStructure());

            name = formVersion.getFormId().getFormName();
            descriptionText = formVersion.getFormId().getFormDesc() == null ? formVersion.getFormId().getFormName() : formVersion.getFormId().getFormDesc();

            return ok(String.format("<xforms xmlns=\"http://openrosa.org/xforms/xformsList\"> <xform> <formID>%s</formID> <name>%s</name> <version>%s</version> <hash>%s</hash> <descriptionText>%s</descriptionText> <downloadUrl>%s</downloadUrl> </xform> </xforms>", formIdVersionId, name, formIdVersionId, md5, descriptionText, downloadUrl)).as("application/xml");
        } catch (ResourceNotFound resourceNotFound) {
            return notFound(resourceNotFound.getMessage());
        }

    }

    @ApiOperation(value = "Upload Form Data as MultipartFormData ",
            httpMethod = "POST")
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Done", response = Void.class)
            }
    )
    @ApiImplicitParams(
            {
                @ApiImplicitParam(
                        name = "body",
                        dataType = "java.io.File",
                        required = true,
                        paramType = "body",
                        value = "FormData"
                )
            }
    )
    @BodyParser.Of(value = BodyParser.MultipartFormData.class)

    public Result submitFormData() {
        try {

            Http.MultipartFormData body = request().body().asMultipartFormData();

            Http.MultipartFormData.FilePart xml_submission_file = body.getFile("xml_submission_file");
            List<Http.MultipartFormData.FilePart> files = body.getFiles();

            Http.MultipartFormData.FilePart<File>[] attatchements = files.parallelStream().filter((filePart) -> filePart != null && !filePart.getFilename().equalsIgnoreCase("xml_submission_file")).collect(Collectors.toList()).toArray(new Http.MultipartFormData.FilePart[]{});

            if (xml_submission_file == null) {
                return notFound(String.format("submission File  not found"));
            }
            File file = (File) xml_submission_file.getFile();
            String xmlData = String.join("", Files.readAllLines(Paths.get(file.getAbsolutePath())).toArray(new String[]{}));
            JsonNode jsonNode = objectMapper.readValue(XML.toJSONObject(xmlData).toString(), JsonNode.class);
            String parent = null;
            if (jsonNode.fieldNames().hasNext()) {
                parent = jsonNode.fieldNames().next();
            }
            if (Objects.nonNull(parent)) {
                JsonNode root = jsonNode.get(parent);
                ((ObjectNode) root).put("form_no", RandomStringUtils.random(6));
                String data = objectMapper.writeValueAsString(root);
                String formVersionId = null;
                String appUserId = null;
                if (root.has("version")) {
                    Pair<String, String> pair = GET_APPUSERID_AND_FORMVERSIONID.apply(root.get("version").asText());
                    appUserId = pair.getValue0();
                    formVersionId = pair.getValue1();
                } else if (root.has("-version")) {
                    Pair<String, String> pair = GET_APPUSERID_AND_FORMVERSIONID.apply(root.get("-version").asText());
                    appUserId = pair.getValue0();
                    formVersionId = pair.getValue1();
                } else {
                    return notFound("Version Id not found");
                }

                if (root.has("login_details")) {
                    AppUser appUser = new AppUser();
                    appUser.setAppUserId(appUserId);
                    appUser.setUserName(root.get("login_details").get("teacher_registration_number").asText());
                    appUser.setPassword(root.get("login_details").get("password").asText());
                    appUser.setRoleName(AuthorizationRole.TEACHER.name());
                    appUser.setFirstName(root.get("personal_information").get("first_name").asText());
                    appUser.setLastName(root.get("personal_information").get("surname").asText());
                    appUser.setMobileNumber(root.get("personal_information").get("mobile_number").asText());

                    appUserManager.addAppUser(appUser);
                }

                applicationManager.addApplicationData(appUserId, formVersionId, xmlData, data, attatchements);

                paymentManager.initializePayment(appUserId, "online", "remitta", null);

                return status(201, "<OpenRosaResponse xmlns=\"http://openrosa.org/http/response\">\n"
                        + "        <message>Form Saved!</message>\n"
                        + "    </OpenRosaResponse>").as("application/xml");
            }
            return notFound(String.format("Cant find root in %s from json %s", xmlData, XML.toJSONObject(xmlData).toString()));

        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError(e.getMessage());
        }

    }

}
