/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "sms_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaSmsLog.findAll", query = "SELECT j FROM JpaSmsLog j")})
public class JpaSmsLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sms_log_id")
    private Integer smsLogId;
    @Size(max = 20)
    @Column(name = "subject")
    private String subject;
    @Size(max = 10)
    @Column(name = "status")
    private String status;
    @Size(max = 2147483647)
    @Column(name = "body")
    private String body;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public JpaSmsLog() {
    }

    public JpaSmsLog(Integer smsLogId) {
        this.smsLogId = smsLogId;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smsLogId != null ? smsLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaSmsLog)) {
            return false;
        }
        JpaSmsLog other = (JpaSmsLog) object;
        if ((this.smsLogId == null && other.smsLogId != null) || (this.smsLogId != null && !this.smsLogId.equals(other.smsLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaSmsLog[ smsLogId=" + smsLogId + " ]";
    }
    
}
