/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "form")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaForm.findAll", query = "SELECT j FROM JpaForm j")})
public class JpaForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "form_id")
    private String formId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "form_name")
    private String formName;
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
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Size(max = 2147483647)
    @Column(name = "form_desc")
    private String formDesc;
    @Size(max = 64)
    @Column(name = "modified_by")
    private String modifiedBy;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jpaForm", fetch = FetchType.LAZY)
    private JpaFormVersion jpaFormVersion;

    public JpaForm() {
    }

    public JpaForm(String formId) {
        this.formId = formId;
    }

    public JpaForm(String formId, String formName, Date dateCreated, String createdBy) {
        this.formId = formId;
        this.formName = formName;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
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

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getFormDesc() {
        return formDesc;
    }

    public void setFormDesc(String formDesc) {
        this.formDesc = formDesc;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public JpaFormVersion getJpaFormVersion() {
        return jpaFormVersion;
    }

    public void setJpaFormVersion(JpaFormVersion jpaFormVersion) {
        this.jpaFormVersion = jpaFormVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formId != null ? formId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaForm)) {
            return false;
        }
        JpaForm other = (JpaForm) object;
        if ((this.formId == null && other.formId != null) || (this.formId != null && !this.formId.equals(other.formId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaForm[ formId=" + formId + " ]";
    }
    
}
