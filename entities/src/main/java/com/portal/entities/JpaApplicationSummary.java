/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "application_summary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationSummary.findAll", query = "SELECT a FROM JpaApplicationSummary a")})
public class JpaApplicationSummary implements Serializable {

    @Column(name = "certificates")
    private String certificates;
    @Column(name = "application_date_created")
    private String applicationDateCreated;
    @Column(name = "payment_id")
    private String paymentId;
    @Size(max = 2147483647)
    @Column(name = "qualifications_with_date")
    private String qualificationsWithDate;
    private static final long serialVersionUID = 1L;
    @Size(max = 64)
    @Column(name = "app_user_id")
    @Id
    private String appUserId;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Size(max = 64)
    @Column(name = "user_name")
    private String userName;
    @Column(name = "filled_form")
    private Boolean filledForm;
    @Size(max = 2147483647)
    @Column(name = "category")
    private String category;
    @Size(max = 2147483647)
    @Column(name = "sur_name")
    private String surName;
    @Size(max = 2147483647)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 2147483647)
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 2147483647)
    @Column(name = "sex")
    private String sex;
    @Size(max = 2147483647)
    @Column(name = "mobile_number")
    private String mobileNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "e_mail")
    private String eMail;
    @Size(max = 2147483647)
    @Column(name = "nationality")
    private String nationality;
    @Size(max = 2147483647)
    @Column(name = "state_of_origin")
    private String stateOfOrigin;
    @Size(max = 2147483647)
    @Column(name = "lga_of_origin")
    private String lgaOfOrigin;
    @Size(max = 2147483647)
    @Column(name = "current_employer")
    private String currentEmployer;
    @Size(max = 2147483647)
    @Column(name = "current_employer_office_address")
    private String currentEmployerOfficeAddress;
    @Size(max = 2147483647)
    @Column(name = "area_of_specialization")
    private String areaOfSpecialization;
    @Size(max = 2147483647)
    @Column(name = "form_no")
    private String formNo;
    @Size(max = 2147483647)
    @Column(name = "institutions_attended")
    private String institutionsAttended;
    @Size(max = 2147483647)
    @Column(name = "work_experience")
    private String workExperience;
    @Column(name = "date_paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;
    @Size(max = 128)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 128)
    @Column(name = "bank_teller")
    private String bankTeller;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Column(name = "date_initialized")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInitialized;

    public JpaApplicationSummary() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getFilledForm() {
        return filledForm;
    }

    public void setFilledForm(Boolean filledForm) {
        this.filledForm = filledForm;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public String getLgaOfOrigin() {
        return lgaOfOrigin;
    }

    public void setLgaOfOrigin(String lgaOfOrigin) {
        this.lgaOfOrigin = lgaOfOrigin;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public String getCurrentEmployerOfficeAddress() {
        return currentEmployerOfficeAddress;
    }

    public void setCurrentEmployerOfficeAddress(String currentEmployerOfficeAddress) {
        this.currentEmployerOfficeAddress = currentEmployerOfficeAddress;
    }

    public String getAreaOfSpecialization() {
        return areaOfSpecialization;
    }

    public void setAreaOfSpecialization(String areaOfSpecialization) {
        this.areaOfSpecialization = areaOfSpecialization;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getInstitutionsAttended() {
        return institutionsAttended;
    }

    public void setInstitutionsAttended(String institutionsAttended) {
        this.institutionsAttended = institutionsAttended;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankTeller() {
        return bankTeller;
    }

    public void setBankTeller(String bankTeller) {
        this.bankTeller = bankTeller;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDateInitialized() {
        return dateInitialized;
    }

    public void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
    }

    public String getQualificationsWithDate() {
        return qualificationsWithDate;
    }

    public void setQualificationsWithDate(String qualificationsWithDate) {
        this.qualificationsWithDate = qualificationsWithDate;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getApplicationDateCreated() {
        return applicationDateCreated;
    }

    public void setApplicationDateCreated(String applicationDateCreated) {
        this.applicationDateCreated = applicationDateCreated;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    
}
