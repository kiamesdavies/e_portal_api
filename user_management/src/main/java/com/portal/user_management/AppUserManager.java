/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.user_management;

import com.github.markserrano.jsonquery.jpa.enumeration.OrderEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.github.markserrano.jsonquery.jpa.service.IFilterService;
import com.github.markserrano.jsonquery.jpa.specifier.Order;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;
import com.google.common.collect.ImmutableMap;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.OrderSpecifier;
import com.portal.commons.exceptions.InvalidCredentialsException;
import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppUser;
import com.portal.commons.models.Category;
import com.portal.commons.models.GeneralMapper;
import com.portal.commons.util.CycleAvoidingMappingContext;
import com.portal.commons.util.MyObjectMapper;
import com.portal.entities.JpaAppUser;
import com.portal.entities.JpaCategory;
import com.portal.entities.QJpaAppUser;
import com.portal.entities.QJpaCategory;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import mock.springframework.data.domain.Page;
import mock.springframework.data.domain.PageRequest;
import mock.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import play.api.libs.ws.WSClient;
import play.db.jpa.JPAApi;

/**
 *
 * @author poseidon
 */
public class AppUserManager {

    @Inject
    JPAApi jPAApi;

    @Inject
    JpaAppUserRepository appUserRepository;

    @Inject
    WSClient wSClient;

    @Inject
    MyObjectMapper objectMapper;

    @Inject
    IFilterService filterService;

    public AppUser addAppUser(AppUser appUser) {
        try {
            if (StringUtils.isNoneBlank(appUser.getUserName())) {

                JpaAppUser jpaAppUser = appUserRepository.getJpaAppUser(appUser.getUserName());

                return GeneralMapper.INSTANCE.jpaAppUserToGeneratedAppUser(jpaAppUser, new CycleAvoidingMappingContext());
            }
        } catch (ResourceNotFound ex) {

        }

        if(StringUtils.isBlank(appUser.getAppUserId())){
            appUser.setAppUserId(java.util.UUID.randomUUID().toString());
        }
        appUser.setCreatedBy(getContextAppUser().orElse(new AppUser("System")).getAppUserId());
        appUser.setDateCreated(new Date());
        appUser.setActive(Boolean.TRUE);
        if (Objects.isNull(appUser.getRegistrationDate())) {
            appUser.setRegistrationDate(new Date());
        }
        try {
            appUser.password(PasswordHash.createHash(appUser.getPassword()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {

            throw new RuntimeException("System failed to generate password");
        }
        jPAApi.em().persist(GeneralMapper.INSTANCE.generatedAppUserToJpaAppUser(appUser, new CycleAvoidingMappingContext()));
        return appUser;
    }

    public void editAppUser(String appUserId, AppUser appUser) throws ResourceNotFound {
        JpaAppUser jpaAppUser = appUserRepository.getJpaAppUser(appUserId);
        jpaAppUser.setFirstName(appUser.getFirstName());
        jpaAppUser.setLastName(appUser.getLastName());
        jpaAppUser.setMobileNumber(appUser.getMobileNumber());

        jpaAppUser.setDateModified(new Date());
        jpaAppUser.setModifiedBy("Self");
        jPAApi.em().merge(jpaAppUser);

    }

    public void editAppUser(String appUserId, String fieldName, String fieldValue) {
        QJpaAppUser appUser = QJpaAppUser.jpaAppUser;
        ImmutableMap<String, StringPath> paths = ImmutableMap.of("firstname", appUser.firstName, "lastname", appUser.lastName, "mobilenumber", appUser.mobileNumber);
        if (!paths.containsKey(fieldName)) {
            throw new RuntimeException(String.format("Field %s not found, supported fields are %s", fieldName, String.join(",", paths.keySet().toArray(new String[]{}))));
        }

        long execute = new JPAQueryFactory(jPAApi.em()).update(appUser)
                .set(paths.get(fieldName), fieldValue)
                .set(appUser.dateModified, new Date())
                .set(appUser.modifiedBy, getContextAppUser().get().getUserName())
                .where(appUser.appUserId.equalsIgnoreCase(appUserId)).execute();
        if (execute < 1) {

        }

    }

    public void changePassword(String username, String oldPassword, String newPassword) throws InvalidCredentialsException, ResourceNotFound {
        try {
            JpaAppUser jpaAppUser = appUserRepository.getJpaAppUser(username);

            if (PasswordHash.validatePassword(oldPassword, jpaAppUser.getPassword())) {
                jpaAppUser.setPassword(PasswordHash.createHash(newPassword));
                jpaAppUser.setDateModified(new Date());
                jpaAppUser.setModifiedBy(username);
                jPAApi.em().merge(jpaAppUser);
                return;
            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {

        }
        throw new InvalidCredentialsException("");
    }

    public JqgridResponse<AppUser> getAppUsers(Boolean search, String filters,
            Integer page, Integer rows, String sidx, String sord) {
        filters = filters == null || filters == "" || !search ? QueryUtil.INIT_FILTER : filters;
        Order order = new Order(JpaAppUser.class);
        OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
        JsonFilter jsonFilter = new JsonFilter(filters);
        BooleanBuilder build = filterService.getJsonBooleanBuilder(JpaAppUser.class).build(jsonFilter);
        if (rows == null) {
            rows = filterService.count(build, JpaAppUser.class, orderSpecifier).intValue();
        }
        Pageable pageable = new PageRequest(page >= 1 ? page - 1 : 0, rows > 0 ? rows : 1);
        Page<JpaAppUser> results = filterService.readAndCount(build, pageable, JpaAppUser.class, orderSpecifier);
        JqgridResponse<AppUser> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(existingEntity -> GeneralMapper.INSTANCE.jpaAppUserToGeneratedAppUser(existingEntity, new CycleAvoidingMappingContext())).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }

    
    public AppUser getAppUser(String appUserId) throws ResourceNotFound {
        
        return GeneralMapper.INSTANCE.jpaAppUserToGeneratedAppUser(appUserRepository.getJpaAppUser(appUserId), new CycleAvoidingMappingContext());
    }
    
    public void deleteAppUser(String appUserId) {
        QJpaAppUser qJpaAppUser = QJpaAppUser.jpaAppUser;
        new JPAQueryFactory(jPAApi.em()).delete(qJpaAppUser).where(qJpaAppUser.appUserId.equalsIgnoreCase(appUserId).or(qJpaAppUser.userName.equalsIgnoreCase(appUserId))).execute();
    }

    /**
     * This method is experimental
     *
     * @return
     */
    public Optional<AppUser> getContextAppUser() {
        AppUser val = null;
        try {
            if (play.mvc.Http.Context.current() != null) {
                val = (AppUser) play.mvc.Http.Context.current().args.get("appUser");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(val);
    }
}
