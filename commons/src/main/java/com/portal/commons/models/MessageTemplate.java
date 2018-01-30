/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class MessageTemplate {

    @NotNull
    @Size(min = 1, max = 64)
    private String messageTemplateId;
    private String messageTemplateName;
    private String messageTemplateDescription;
    @NotNull
    private String mailTemplate;
    @NotNull
    private String smsTemplate;

    private boolean isDefault = true;

    public MessageTemplate() {
    }

    public MessageTemplate(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    public String getMessageTemplateId() {
        return messageTemplateId;
    }

    public void setMessageTemplateId(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    public String getMessageTemplateName() {
        return messageTemplateName;
    }

    public void setMessageTemplateName(String messageTemplateName) {
        this.messageTemplateName = messageTemplateName;
    }

    public String getMessageTemplateDescription() {
        return messageTemplateDescription;
    }

    public void setMessageTemplateDescription(String messageTemplateDescription) {
        this.messageTemplateDescription = messageTemplateDescription;
    }

    public String getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(String smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
