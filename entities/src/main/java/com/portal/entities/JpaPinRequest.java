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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
    @EmbeddedId
    protected JpaPinRequestPK jpaPinRequestPK;
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
    @JoinColumn(name = "app_user_id", referencedColumnName = "app_user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaAppUser jpaAppUser;

    public JpaPinRequest() {
    }

    public JpaPinRequest(JpaPinRequestPK jpaPinRequestPK) {
        this.jpaPinRequestPK = jpaPinRequestPK;
    }

    public JpaPinRequest(JpaPinRequestPK jpaPinRequestPK, boolean used, Date dateCreated) {
        this.jpaPinRequestPK = jpaPinRequestPK;
        this.used = used;
        this.dateCreated = dateCreated;
    }

    public JpaPinRequest(String pinId, String appUserId) {
        this.jpaPinRequestPK = new JpaPinRequestPK(pinId, appUserId);
    }

    public JpaPinRequestPK getJpaPinRequestPK() {
        return jpaPinRequestPK;
    }

    public void setJpaPinRequestPK(JpaPinRequestPK jpaPinRequestPK) {
        this.jpaPinRequestPK = jpaPinRequestPK;
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

    public JpaAppUser getJpaAppUser() {
        return jpaAppUser;
    }

    public void setJpaAppUser(JpaAppUser jpaAppUser) {
        this.jpaAppUser = jpaAppUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jpaPinRequestPK != null ? jpaPinRequestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaPinRequest)) {
            return false;
        }
        JpaPinRequest other = (JpaPinRequest) object;
        if ((this.jpaPinRequestPK == null && other.jpaPinRequestPK != null) || (this.jpaPinRequestPK != null && !this.jpaPinRequestPK.equals(other.jpaPinRequestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaPinRequest[ jpaPinRequestPK=" + jpaPinRequestPK + " ]";
    }
    
}
