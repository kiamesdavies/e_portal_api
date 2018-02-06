/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.configuration;

import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.MessageTemplate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public interface IMessageTemplate {

    static enum MessageType {

        PIN_RESET,
        CERTICIATE,
        PAYMENT_CLAIMED,
        
        EXPIRED_PAYMENT,
        
        PAYMENT_RENEWAL_STARTED

    }

    MessageTemplate getMessageTemplate(MessageType messageType) throws ResourceNotFound;

    MessageTemplate getMessageTemplate(String messageTemplateId) throws ResourceNotFound;

    List<MessageTemplate> getMessageTemplates();

    String parseMessageTemplate(String template, Object o);

    String parseMessageTemplateMap(String template, Map<String, Object> map);
}
