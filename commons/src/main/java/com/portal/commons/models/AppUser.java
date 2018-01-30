package com.portal.commons.models;

import com.portal.admin.models.ApplicationData;
import com.portal.entities.JpaAppUser;
import java.lang.Boolean;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.072+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaAppUser"
)
public class AppUser {

    public static String getJpaName(JpaAppUser jpaAppUser) {
      return String.format("%s %s", Objects.toString(jpaAppUser.getFirstName(), ""),  Objects.toString(jpaAppUser.getLastName(), "")).trim();
    }

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String appUserId;

    @Size(
            max = 64,
            min = 0
    )
    private String firstName;

    @Size(
            max = 64,
            min = 0
    )
    private String lastName;

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String userName;

    @Size(
            max = 128,
            min = 1
    )
    @NotNull
    private String password;

    @Size(
            max = 15,
            min = 0
    )
    private String mobileNumber;

    private Date dateCreated;

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String createdBy;

    private Date dateModified;

    @Size(
            max = 64,
            min = 0
    )
    private String modifiedBy;

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String roleName;

    private Boolean active;

    private ApplicationData jpaApplicationData;

    public String getAppUserId() {
        return this.appUserId;
    }

    public Void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
        return null;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Void setFirstName(String firstName) {
        this.firstName = firstName;
        return null;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Void setLastName(String lastName) {
        this.lastName = lastName;
        return null;
    }

    public String getUserName() {
        return this.userName;
    }

    public Void setUserName(String userName) {
        this.userName = userName;
        return null;
    }

    public String getPassword() {
        return this.password;
    }

    public Void setPassword(String password) {
        this.password = password;
        return null;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public Void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public Date getDateModified() {
        return this.dateModified;
    }

    public Void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
        return null;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public Void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return null;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Void setRoleName(String roleName) {
        this.roleName = roleName;
        return null;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Void setActive(Boolean active) {
        this.active = active;
        return null;
    }

    public ApplicationData getJpaApplicationData() {
        return this.jpaApplicationData;
    }

    public Void setJpaApplicationData(ApplicationData jpaApplicationData) {
        this.jpaApplicationData = jpaApplicationData;
        return null;
    }

    public AppUser appUserId(final String value) {
        this.appUserId = value;
        return this;
    }

    public AppUser firstName(final String value) {
        this.firstName = value;
        return this;
    }

    public AppUser lastName(final String value) {
        this.lastName = value;
        return this;
    }

    public AppUser userName(final String value) {
        this.userName = value;
        return this;
    }

    public AppUser password(final String value) {
        this.password = value;
        return this;
    }

    public AppUser mobileNumber(final String value) {
        this.mobileNumber = value;
        return this;
    }

    public AppUser dateCreated(final Date value) {
        this.dateCreated = value;
        return this;
    }

    public AppUser createdBy(final String value) {
        this.createdBy = value;
        return this;
    }

    public AppUser dateModified(final Date value) {
        this.dateModified = value;
        return this;
    }

    public AppUser modifiedBy(final String value) {
        this.modifiedBy = value;
        return this;
    }

    public AppUser roleName(final String value) {
        this.roleName = value;
        return this;
    }

    public AppUser active(final Boolean value) {
        this.active = value;
        return this;
    }

    public AppUser jpaApplicationData(final ApplicationData value) {
        this.jpaApplicationData = value;
        return this;
    }

}
