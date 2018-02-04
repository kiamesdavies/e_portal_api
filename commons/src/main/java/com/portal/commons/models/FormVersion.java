package com.portal.commons.models;

import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;

@Generated(
        date = "2018-01-30T11:51:55.315+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaFormVersion"
)
public class FormVersion {

    private String jsonStructure;
    private String xformStructure;

    private String formVersionId;

    private Date dateCreated;

    private String createdBy;

    private Form formId;

    private String previewUrl;

    public FormVersion() {
    }

    public FormVersion(String formVersionId) {
        this.formVersionId = formVersionId;
    }

    
    
    
    public String getFormVersionId() {
        return this.formVersionId;
    }

    public Void setFormVersionId(String formVersionId) {
        this.formVersionId = formVersionId;
        return null;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public Void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return null;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return null;
    }

    public String getPreviewUrl() {
        return this.previewUrl;
    }

    public Void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
        return null;
    }

    public Form getFormId() {
        return formId;
    }

    public void setFormId(Form formId) {
        this.formId = formId;
    }

    public String getJsonStructure() {
        return jsonStructure;
    }

    public void setJsonStructure(String jsonStructure) {
        this.jsonStructure = jsonStructure;
    }

    public String getXformStructure() {
        return xformStructure;
    }

    public void setXformStructure(String xformStructure) {
        this.xformStructure = xformStructure;
    }

}
