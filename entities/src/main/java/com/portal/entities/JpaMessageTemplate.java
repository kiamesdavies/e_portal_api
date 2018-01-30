/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "message_template")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaMessageTemplate.findAll", query = "SELECT j FROM JpaMessageTemplate j")})
public class JpaMessageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "message_template_id")
    private String messageTemplateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "message_template_name")
    private String messageTemplateName;
    @Size(max = 2147483647)
    @Column(name = "message_template_description")
    private String messageTemplateDescription;
    @Column(name = "mail_template", columnDefinition = "xml")
    @Convert(converter = XmlStringConverter.class)
    private String mailTemplate;
    @Column(name = "sms_template", columnDefinition = "xml")
    @Convert(converter = XmlStringConverter.class)
    private String smsTemplate;

    public JpaMessageTemplate() {
    }

    public JpaMessageTemplate(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    public JpaMessageTemplate(String messageTemplateId, String messageTemplateName) {
        this.messageTemplateId = messageTemplateId;
        this.messageTemplateName = messageTemplateName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageTemplateId != null ? messageTemplateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaMessageTemplate)) {
            return false;
        }
        JpaMessageTemplate other = (JpaMessageTemplate) object;
        if ((this.messageTemplateId == null && other.messageTemplateId != null) || (this.messageTemplateId != null && !this.messageTemplateId.equals(other.messageTemplateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaMessageTemplate[ messageTemplateId=" + messageTemplateId + " ]";
    }

}
