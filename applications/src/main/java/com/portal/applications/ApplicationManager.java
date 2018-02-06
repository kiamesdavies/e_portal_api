/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.applications;

import com.github.markserrano.jsonquery.jpa.enumeration.OrderEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.github.markserrano.jsonquery.jpa.service.IFilterService;
import com.github.markserrano.jsonquery.jpa.specifier.Order;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;
import com.google.common.collect.ImmutableMap;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.OrderSpecifier;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.ApplicationData;
import com.portal.commons.models.ApplicationSummary;
import com.portal.commons.models.Form;
import com.portal.commons.models.FormVersion;
import com.portal.commons.models.GeneralMapper;
import com.portal.commons.util.CycleAvoidingMappingContext;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.entities.JpaApplicationCount;
import com.portal.entities.JpaApplicationData;
import com.portal.entities.JpaApplicationSummary;
import com.portal.entities.JpaFormVersion;
import com.portal.entities.QJpaApplicationCount;
import com.portal.entities.QJpaApplicationDashboard;
import com.portal.entities.QJpaApplicationData;
import com.portal.entities.QJpaApplicationSummary;
import com.portal.entities.QJpaForm;
import com.portal.entities.QJpaFormVersion;
import com.portal.user_management.AppUserManager;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.inject.Inject;
import mock.springframework.data.domain.Page;
import mock.springframework.data.domain.PageRequest;
import mock.springframework.data.domain.Pageable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.javatuples.Pair;
import play.db.jpa.JPAApi;
import play.mvc.Http;

/**
 *
 * @author poseidon
 */
public class ApplicationManager {

    @Inject
    JPAApi jPAApi;

    @Inject
    IFilterService filterService;

    @Inject
    AppUserManager appUserManager;

    public HashMap<String, Object> getDashboard() {
        QJpaApplicationDashboard qjad = QJpaApplicationDashboard.jpaApplicationDashboard;
        QJpaApplicationCount qjac = QJpaApplicationCount.jpaApplicationCount;
        QJpaApplicationSummary qjas = QJpaApplicationSummary.jpaApplicationSummary;
        JpaApplicationCount fetchFirst = new JPAQueryFactory(jPAApi.em()).selectFrom(qjac).fetchFirst();
        HashMap<String, Object> map = new HashMap<>();
        if (fetchFirst != null) {
            map.put("pie", GeneralMapper.INSTANCE.jpaApplicationCountToApplicationCount(fetchFirst));
        }
        map.put("bar", new JPAQueryFactory(jPAApi.em()).selectFrom(qjad).fetch().stream().map(GeneralMapper.INSTANCE::jpaApplicationDashboardToApplicationDashboard).collect(Collectors.toList()));

        List<Tuple> fetch = new JPAQueryFactory(jPAApi.em()).from(qjas).groupBy(qjas.category).select(qjas.category, qjas.category.count()).fetch();

        map.put("categories", fetch.stream().map(g -> ImmutableMap.of("category", Objects.toString(g.get(0, String.class), "No Category"), "count", g.get(1, Long.class))).collect(Collectors.toList()));

        return map;
    }

    public ApplicationSummary getApplicationSummary(String appUserId) throws ResourceNotFound {
        QJpaApplicationSummary qas = QJpaApplicationSummary.jpaApplicationSummary;

        JpaApplicationSummary fetchOne = new JPAQueryFactory(jPAApi.em()).selectFrom(qas).where(qas.appUserId.equalsIgnoreCase(appUserId)).fetchOne();
        if (fetchOne == null) {
            throw new ResourceNotFound(String.format("Application %s not found", appUserId));
        }
        return GeneralMapper.INSTANCE.jpaApplicationSummaryToApplicationSummary(fetchOne);
    }

    public ApplicationData getApplicationData(String appUserId) throws ResourceNotFound {
        QJpaApplicationData qas = QJpaApplicationData.jpaApplicationData;

        JpaApplicationData fetchOne = new JPAQueryFactory(jPAApi.em()).selectFrom(qas).where(qas.appUserId.equalsIgnoreCase(appUserId)).fetchOne();
        if (fetchOne == null) {
            throw new ResourceNotFound(String.format("Application %s not found", appUserId));
        }
        return GeneralMapper.INSTANCE.jpaApplicationDataToGeneratedApplicationData(fetchOne, new CycleAvoidingMappingContext());
    }

    public JqgridResponse<ApplicationSummary> getApplications(Boolean search, String filters,
            Integer page, Integer rows, String sidx, String sord) {
        filters = filters == null || filters == "" || !search ? QueryUtil.INIT_FILTER : filters;
        Order order = new Order(JpaApplicationSummary.class);
        OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
        JsonFilter jsonFilter = new JsonFilter(filters);
        BooleanBuilder build = filterService.getJsonBooleanBuilder(JpaApplicationSummary.class).build(jsonFilter);
        if (rows == null) {
            rows = filterService.count(build, JpaApplicationSummary.class, orderSpecifier).intValue();
        }
        Pageable pageable = new PageRequest(page >= 1 ? page - 1 : 0, rows > 0 ? rows : 1);
        Page<JpaApplicationSummary> results = filterService.readAndCount(build, pageable, JpaApplicationSummary.class, orderSpecifier);
        JqgridResponse<ApplicationSummary> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(GeneralMapper.INSTANCE::jpaApplicationSummaryToApplicationSummary).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }

    public FormVersion addFormVersion(String formId, String formVersionId, String jsonStructure, String xformStructure, String previewUrl) {
        FormVersion formVersion = new FormVersion();
        formVersion.setJsonStructure(jsonStructure);
        formVersion.setXformStructure(xformStructure);
        formVersion.setPreviewUrl(previewUrl);
        formVersion.setFormVersionId(formVersionId);
        formVersion.setFormId(new Form(formId));
        formVersion.setDateCreated(new Date());
        formVersion.setCreatedBy(appUserManager.getContextAppUser().get().getUserName());

        jPAApi.em().persist(GeneralMapper.INSTANCE.generatedFormVersionToJpaFormVersion(formVersion, new CycleAvoidingMappingContext()));
        jPAApi.em().flush();

        return formVersion;
    }
    private static final SimpleDateFormat MONTH_DATEFORMAT = new SimpleDateFormat("yyyy-MM");

    private String interpolateAttachementsWithFormData(String formData, Http.MultipartFormData.FilePart<File>... attachments) {

        Map<String, String> fileNameAndKeyPairs = Arrays.asList(attachments).parallelStream().map(f -> {
            String newFilePath = MONTH_DATEFORMAT.format(new Date()) + "/" + RandomStringUtils.randomAlphanumeric(10) + "_" + (f.getFilename().trim().replace(" ", "_"));
            String fullPath = EnvironMentVariables.STORAGE_PATH + newFilePath;
            new File(fullPath).getParentFile().mkdirs();
            String filename = f.getFilename();
            try {
                FileUtils.moveFile(f.getFile(), new File(fullPath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return Pair.with(filename, newFilePath);
        }).collect(Collectors.toMap(z -> z.getValue0(), g -> g.getValue1()));

        Function<String, String> combined = fileNameAndKeyPairs.entrySet().stream()
                .reduce(
                        Function.identity(),
                        (f, e) -> s -> f.apply(s).replace(e.getKey(), e.getValue()),
                        Function::andThen
                );
        return combined.apply(formData);
    }

    public ApplicationData addApplicationData(String appUserId, String formVersionId, String xmlData, String jsonData, Http.MultipartFormData.FilePart... attachments) {
        ApplicationData applicationData = new ApplicationData();
        applicationData.setAppUserId(appUserId);
        applicationData.setXmlFormData(xmlData);
        applicationData.setFormData(interpolateAttachementsWithFormData(jsonData, attachments));

        applicationData.setFormVersionId(new FormVersion(formVersionId));

        applicationData.setCreatedBy(appUserManager.getContextAppUser().orElse(new AppUser("System")).getAppUserId());
        applicationData.setDateCreated(new Date());

        jPAApi.em().merge(GeneralMapper.INSTANCE.generatedApplicationDataToJpaApplicationData(applicationData, new CycleAvoidingMappingContext()));
        return applicationData;

    }

    public void deleteFormVersion(String formVersionId) {

        new JPAQueryFactory(jPAApi.em()).delete(QJpaFormVersion.jpaFormVersion).where(QJpaFormVersion.jpaFormVersion.formVersionId.equalsIgnoreCase(formVersionId)).execute();
    }

    public FormVersion getFormVersion(String formVersionId) throws ResourceNotFound {

        JpaFormVersion fetchOne = new JPAQueryFactory(jPAApi.em()).selectFrom(QJpaFormVersion.jpaFormVersion).where(QJpaFormVersion.jpaFormVersion.formVersionId.equalsIgnoreCase(formVersionId)).fetchOne();
        if (fetchOne == null) {
            throw new ResourceNotFound(String.format("FormVersionId %s not found ", formVersionId));
        }

        return GeneralMapper.INSTANCE.jpaFormVersionToGeneratedFormVersion(fetchOne, new CycleAvoidingMappingContext());
    }

    public FormVersion getLastestFormVersion(String formId) throws ResourceNotFound {
        QJpaFormVersion qjfv = QJpaFormVersion.jpaFormVersion;
        JpaFormVersion formVersionId = new JPAQueryFactory(jPAApi.em()).selectFrom(qjfv).orderBy(qjfv.dateCreated.desc()).join(qjfv.formId, QJpaForm.jpaForm).where(QJpaForm.jpaForm.formId.equalsIgnoreCase(formId)).fetchFirst();
        if (formVersionId == null) {
            throw new ResourceNotFound(String.format("Latest FormVersion Id form for formId %s not found", formId));
        }
        return GeneralMapper.INSTANCE.jpaFormVersionToGeneratedFormVersion(formVersionId, new CycleAvoidingMappingContext());
    }

    public JqgridResponse<FormVersion> getFormVersions(Boolean search, String filters,
            Integer page, Integer rows, String sidx, String sord) {
        filters = filters == null || filters == "" || !search ? QueryUtil.INIT_FILTER : filters;
        Order order = new Order(JpaFormVersion.class);
        OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
        JsonFilter jsonFilter = new JsonFilter(filters);
        BooleanBuilder build = filterService.getJsonBooleanBuilder(JpaFormVersion.class).build(jsonFilter);
        if (rows == null) {
            rows = filterService.count(build, JpaFormVersion.class, orderSpecifier).intValue();
        }
        Pageable pageable = new PageRequest(page >= 1 ? page - 1 : 0, rows > 0 ? rows : 1);
        Page<JpaFormVersion> results = filterService.readAndCount(build, pageable, JpaFormVersion.class, orderSpecifier);
        JqgridResponse<FormVersion> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(s -> GeneralMapper.INSTANCE.jpaFormVersionToGeneratedFormVersion(s, new CycleAvoidingMappingContext())).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }

}
