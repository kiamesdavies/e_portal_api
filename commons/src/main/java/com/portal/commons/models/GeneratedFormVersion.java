package com.portal.admin.models;

import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.315+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaFormVersion"
)
public class GeneratedFormVersion {

    private String jsonStructure;
    private String xformStructure;

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String formVersionId;

    private Date dateCreated;

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String createdBy;

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String formId;

    @Size(
            max = 128,
            min = 1
    )
    @NotNull
    private String previewUrl;

    private GeneratedForm jpaForm;

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

    public String getFormId() {
        return this.formId;
    }

    public Void setFormId(String formId) {
        this.formId = formId;
        return null;
    }

    public String getPreviewUrl() {
        return this.previewUrl;
    }

    public Void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
        return null;
    }

    public GeneratedForm getJpaForm() {
        return this.jpaForm;
    }

    public Void setJpaForm(GeneratedForm jpaForm) {
        this.jpaForm = jpaForm;
        return null;
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
