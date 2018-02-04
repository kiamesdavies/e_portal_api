/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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

    @Basic(optional = false)
    @NotNull
    @Column(name = "xform_structure", columnDefinition = "xml")
    @Convert(converter = XmlStringConverter.class)
    private String xformStructure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "json_structure", columnDefinition = "json")
    @Convert(converter = JsonStringConverter.class)
    private String jsonStructure;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formVersionId", fetch = FetchType.LAZY)
    private List<JpaApplicationData> jpaApplicationDataList;

    private static final long serialVersionUID = 1L;

    @Id
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
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaForm formId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "preview_url")
    private String previewUrl;

    public JpaFormVersion() {
    }

    public JpaFormVersion(String formVersionId) {
        this.formVersionId = formVersionId;
    }

    public JpaFormVersion(String formVersionId, Date dateCreated, String createdBy, String jsonStructure, String xformStructure) {
        this.formVersionId = formVersionId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.jsonStructure = jsonStructure;
        this.xformStructure = xformStructure;
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

    public JpaForm getFormId() {
        return formId;
    }

    public void setFormId(JpaForm formId) {
        this.formId = formId;
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

    @XmlTransient
    public List<JpaApplicationData> getJpaApplicationDataList() {
        return jpaApplicationDataList;
    }

    public void setJpaApplicationDataList(List<JpaApplicationData> jpaApplicationDataList) {
        this.jpaApplicationDataList = jpaApplicationDataList;
    }

}
