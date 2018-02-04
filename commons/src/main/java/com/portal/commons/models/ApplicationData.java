package com.portal.commons.models;

import com.portal.commons.models.AppUser;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.354+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaApplicationData"
)
public class ApplicationData {

    private FormVersion formVersionId;
    private String xmlFormData;
    private String formData;
    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String appUserId;

    private Date dateCreated;

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String createdBy;

    private AppUser jpaAppUser;

    public String getAppUserId() {
        return this.appUserId;
    }

    public Void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
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

    public AppUser getJpaAppUser() {
        return this.jpaAppUser;
    }

    public Void setJpaAppUser(AppUser jpaAppUser) {
        this.jpaAppUser = jpaAppUser;
        return null;
    }

    public String getXmlFormData() {
        return xmlFormData;
    }

    public void setXmlFormData(String xmlFormData) {
        this.xmlFormData = xmlFormData;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public FormVersion getFormVersionId() {
        return formVersionId;
    }

    public void setFormVersionId(FormVersion formVersionId) {
        this.formVersionId = formVersionId;
    }

}
