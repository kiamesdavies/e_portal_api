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
@Table(name = "form_version")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaFormVersion.findAll", query = "SELECT j FROM JpaFormVersion j")})
public class JpaFormVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "form_version_id")
    private String formVersionId;
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
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "form_id")
    private String formId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "json_structure", columnDefinition = "json")
    @Convert(converter = JsonStringConverter.class)
    private String jsonStructure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "xform_structure", columnDefinition = "xml")
    @Convert(converter = XmlStringConverter.class)
    private String xformStructure;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "preview_url")
    private String previewUrl;
    @JoinColumn(name = "form_id", referencedColumnName = "form_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private JpaForm jpaForm;

    public JpaFormVersion() {
    }

    public JpaFormVersion(String formId) {
        this.formId = formId;
    }

    public JpaFormVersion(String formId, String formVersionId, Date dateCreated, String createdBy, String xformStructure, String jsonStructure, String previewUrl) {
        this.formId = formId;
        this.formVersionId = formVersionId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.xformStructure = xformStructure;
        this.jsonStructure = jsonStructure;
        this.previewUrl = previewUrl;
    }

    public String getFormVersionId() {
        return formVersionId;
    }

    public void setFormVersionId(String formVersionId) {
        this.formVersionId = formVersionId;
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

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getXformStructure() {
        return xformStructure;
    }

    public void setXformStructure(String xformStructure) {
        this.xformStructure = xformStructure;
    }

    public String getJsonStructure() {
        return jsonStructure;
    }

    public void setJsonStructure(String jsonStructure) {
        this.jsonStructure = jsonStructure;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public JpaForm getJpaForm() {
        return jpaForm;
    }

    public void setJpaForm(JpaForm jpaForm) {
        this.jpaForm = jpaForm;
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
        if (!(object instanceof JpaFormVersion)) {
            return false;
        }
        JpaFormVersion other = (JpaFormVersion) object;
        if ((this.formId == null && other.formId != null) || (this.formId != null && !this.formId.equals(other.formId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaFormVersion[ formId=" + formId + " ]";
    }
    
}
