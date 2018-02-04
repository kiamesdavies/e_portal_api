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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "pin_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaPinRequest.findAll", query = "SELECT j FROM JpaPinRequest j")})
public class JpaPinRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "pin_id")
    private String pinId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "used")
    private boolean used;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_used")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUsed;
    @JoinColumn(name = "app_user_id", referencedColumnName = "app_user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaAppUser appUserId;

    public JpaPinRequest() {
    }

    public JpaPinRequest(String pinId) {
        this.pinId = pinId;
    }

    public JpaPinRequest(String pinId, boolean used, Date dateCreated) {
        this.pinId = pinId;
        this.used = used;
        this.dateCreated = dateCreated;
    }

    public String getPinId() {
        return pinId;
    }

    public void setPinId(String pinId) {
        this.pinId = pinId;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUsed() {
        return dateUsed;
    }

    public void setDateUsed(Date dateUsed) {
        this.dateUsed = dateUsed;
    }

    public JpaAppUser getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(JpaAppUser appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pinId != null ? pinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaPinRequest)) {
            return false;
        }
        JpaPinRequest other = (JpaPinRequest) object;
        if ((this.pinId == null && other.pinId != null) || (this.pinId != null && !this.pinId.equals(other.pinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaPinRequest[ pinId=" + pinId + " ]";
    }
    
}
