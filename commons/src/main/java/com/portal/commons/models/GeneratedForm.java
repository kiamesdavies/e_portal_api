package com.portal.admin.models;

import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
    date = "2018-01-30T11:51:55.345+0100",
    comments = "By Akinniranye James Ayodele",
    value = "Generated from com.portal.entities.JpaForm"
)
public class GeneratedForm {
  @Size(
      max = 64,
      min = 1
  )
  @NotNull
  private String formId;

  @Size(
      max = 64,
      min = 1
  )
  @NotNull
  private String formName;

  private Date dateCreated;

  @Size(
      max = 64,
      min = 1
  )
  @NotNull
  private String createdBy;

  private Date dateModified;

  @Size(
      max = 2147483647,
      min = 0
  )
  private String formDesc;

  @Size(
      max = 64,
      min = 0
  )
  private String modifiedBy;

  private GeneratedFormVersion jpaFormVersion;

  public String getFormId() {
    return this.formId;
  }

  public Void setFormId(String formId) {
    this.formId= formId;
    return null;
  }

  public String getFormName() {
    return this.formName;
  }

  public Void setFormName(String formName) {
    this.formName= formName;
    return null;
  }

  public Date getDateCreated() {
    return this.dateCreated;
  }

  public Void setDateCreated(Date dateCreated) {
    this.dateCreated= dateCreated;
    return null;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public Void setCreatedBy(String createdBy) {
    this.createdBy= createdBy;
    return null;
  }

  public Date getDateModified() {
    return this.dateModified;
  }

  public Void setDateModified(Date dateModified) {
    this.dateModified= dateModified;
    return null;
  }

  public String getFormDesc() {
    return this.formDesc;
  }

  public Void setFormDesc(String formDesc) {
    this.formDesc= formDesc;
    return null;
  }

  public String getModifiedBy() {
    return this.modifiedBy;
  }

  public Void setModifiedBy(String modifiedBy) {
    this.modifiedBy= modifiedBy;
    return null;
  }

  public GeneratedFormVersion getJpaFormVersion() {
    return this.jpaFormVersion;
  }

  public Void setJpaFormVersion(GeneratedFormVersion jpaFormVersion) {
    this.jpaFormVersion= jpaFormVersion;
    return null;
  }
}
