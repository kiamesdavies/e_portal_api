/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.configuration.impl;

import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.util.EnvironMentVariables;
import com.portal.configuration.IMessageTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.commons.models.GeneralMapper;
import com.portal.commons.models.MessageTemplate;
import com.portal.configuration.repository.ConfigRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import play.db.jpa.JPAApi;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class MessageTemplateImpl implements IMessageTemplate {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    VelocityEngine velocityEngine;

    @Inject
    ConfigRepository configRepository;

    @Inject
    JPAApi jPAApi;

    @Override
    public String parseMessageTemplate(String templateData, Object o) {
        Map<String, Object> map = objectMapper.convertValue(o, Map.class);
        return this.parseMessageTemplateMap(templateData, map);
    }

    @Override
    public String parseMessageTemplateMap(String templateData, Map<String, Object> map) {
        final VelocityContext context = new VelocityContext();
        map = new HashMap<>(map);
        map.put("API_SERVER_URL", EnvironMentVariables.API_SERVER_URL);
        map.put("APP_SERVER_URL", EnvironMentVariables.APP_SERVER_URL);

        map.put("COOKIE_SEVER_URL", EnvironMentVariables.COOKIE_SERVER_URL);
        map.put("ENKETO_SEVER_URL", EnvironMentVariables.ENKETO_SERVER_URL);

        //mistakes
        map.put("APP_SEVER_URL", EnvironMentVariables.APP_SERVER_URL);
        map.put("API_SEVER_URL", EnvironMentVariables.API_SERVER_URL);

        map.entrySet().stream().forEach(r -> context.put(r.getKey(), r.getValue()));

        StringResourceRepository repo = (StringResourceRepository) velocityEngine.getApplicationAttribute(StringResourceLoader.REPOSITORY_NAME_DEFAULT);
        repo.putStringResource("woogie2", templateData);
        Template template = velocityEngine.getTemplate("woogie2");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString().trim();
    }

   
    @Override
    public MessageTemplate getMessageTemplate(String messageTemplateId) throws ResourceNotFound {
        return GeneralMapper.INSTANCE.jpaMessageTemplateToGeneratedMessageTemplate(configRepository.getJpaMessageTemplate(messageTemplateId)
        );

    }
    
     


    

    @Override
    public List<MessageTemplate> getMessageTemplates() {
        return configRepository.getJpaMessageTemplates().parallelStream().map(GeneralMapper.INSTANCE::jpaMessageTemplateToGeneratedMessageTemplate).collect(Collectors.toList());
    }

    
    @Override
    public MessageTemplate getMessageTemplate(MessageType messageType) throws ResourceNotFound {
        return this.getMessageTemplate(messageType.name());
    }

    


}
