/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.configuration.repository;

import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.entities.JpaAppConfig;
import com.portal.entities.JpaMessageTemplate;
import com.portal.entities.QJpaAppConfig;
import com.portal.entities.QJpaMessageTemplate;
import com.google.inject.Singleton;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
@Singleton
public class ConfigRepository {

    @Inject
    JPAApi jPAApi;

    public JpaAppConfig getJpaAppConfig(String appConfigId) throws ResourceNotFound {
        JpaAppConfig find = jPAApi.em().find(JpaAppConfig.class, appConfigId);
        if (find == null) {
            throw new ResourceNotFound(String.format("AppConfig %s not found", appConfigId));
        }
        return find;
    }

   
    public List<JpaAppConfig> getJpaAppConfigs() {
        return getUnformattedJpaAppConfigs().parallelStream().collect(Collectors.toList());
    }

    private List<JpaAppConfig> getUnformattedJpaAppConfigs() {
        QJpaAppConfig appConfig = QJpaAppConfig.jpaAppConfig;
        return new JPAQueryFactory(jPAApi.em()).selectFrom(appConfig).fetch();
    }

    private List<JpaAppConfig> getUnformattedJpaAppConfigsForUser() {
        QJpaAppConfig appConfig = QJpaAppConfig.jpaAppConfig;
        return new JPAQueryFactory(jPAApi.em()).selectFrom(appConfig).where(appConfig.isAvailableToUser.isTrue()).fetch();
    }

   

  

    public JpaMessageTemplate getJpaMessageTemplate(String messageTemplateId) throws ResourceNotFound {
        JpaMessageTemplate find = jPAApi.em().find(JpaMessageTemplate.class, messageTemplateId);
      
        if (find == null) {
            throw new ResourceNotFound(String.format("Message Template %s not found", messageTemplateId));
        }
        return find;
    }

   

    public List<JpaMessageTemplate> getJpaMessageTemplates() {
        QJpaMessageTemplate template = QJpaMessageTemplate.jpaMessageTemplate;
        return new JPAQueryFactory(jPAApi.em()).selectFrom(template).fetch();
    }

   

}
