/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.models;

import java.util.Date;

/**
 *
 * @author poseidon
 */
public class SmsLog {

    private Integer smsLogId;

    private String subject;

    private String status;

    private String body;

    private Date dateCreated;

    public Integer getSmsLogId() {
        return smsLogId;
    }

    public void setSmsLogId(Integer smsLogId) {
        this.smsLogId = smsLogId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
