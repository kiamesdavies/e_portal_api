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
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "application_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaApplicationData.findAll", query = "SELECT j FROM JpaApplicationData j")})
public class JpaApplicationData implements Serializable {

  
    @Basic(optional = false)
    @NotNull
    @Column(name = "form_data", columnDefinition = "json")
    @Convert(converter = JsonbStringConverter.class)
    private String formData;
    @Column(name = "xml_form_data", columnDefinition = "xml")
    @Convert(converter = XmlStringConverter.class)
    private String xmlFormData;
    @JoinColumn(name = "form_version_id", referencedColumnName = "form_version_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaFormVersion formVersionId;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "app_user_id")
    private String appUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "created_by")
    private String createdBy;
    @JoinColumn(name = "app_user_id", referencedColumnName = "app_user_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private JpaAppUser jpaAppUser;

    public JpaApplicationData() {
    }

    public JpaApplicationData(String appUserId) {
        this.appUserId = appUserId;
    }

    public JpaApplicationData(String appUserId, Date dateCreated, String createdBy, String formData) {
        this.appUserId = appUserId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.formData = formData;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public String getXmlFormData() {
        return xmlFormData;
    }

    public void setXmlFormData(String xmlFormData) {
        this.xmlFormData = xmlFormData;
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
        hash += (appUserId != null ? appUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaApplicationData)) {
            return false;
        }
        JpaApplicationData other = (JpaApplicationData) object;
        if ((this.appUserId == null && other.appUserId != null) || (this.appUserId != null && !this.appUserId.equals(other.appUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaApplicationData[ appUserId=" + appUserId + " ]";
    }

  
    public JpaFormVersion getFormVersionId() {
        return formVersionId;
    }

    public void setFormVersionId(JpaFormVersion formVersionId) {
        this.formVersionId = formVersionId;
    }

}
