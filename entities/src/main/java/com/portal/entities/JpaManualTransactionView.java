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
import javax.persistence.Lob;
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
@Table(name = "manual_transaction_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaManualTransactionView.findAll", query = "SELECT j FROM JpaManualTransactionView j")})
public class JpaManualTransactionView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 64)
    @Column(name = "transaction_id")
    @Id
    private String transactionId;
    @Size(max = 20)
    @Column(name = "registration_number")
    private String registrationNumber;
    @Size(max = 64)
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Size(max = 64)
    @Column(name = "created_by_app_user_id")
    private String createdByAppUserId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "date_claimed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClaimed;
    @Column(name = "claimed")
    private Boolean claimed;
    @Column(name = "credited_account")
    private Boolean creditedAccount;
    @Size(max = 2147483647)
    @Column(name = "processing_message")
    private String processingMessage;
    @Size(max = 64)
    @Column(name = "receipt")
    private String receipt;
    @Size(max = 128)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 128)
    @Column(name = "bank_teller")
    private String bankTeller;
    @Column(name = "date_paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;
    @Size(max = 64)
    @Column(name = "app_user_id")
    private String appUserId;
    @Column(name = "date_initialized")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInitialized;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Column(name = "paid")
    private Boolean paid;
   
    @Column(name = "payment_type")
    private String paymentType;
    @Size(max = 64)
    @Column(name = "category_id")
    private String categoryId;
    @Size(max = 64)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 64)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 64)
    @Column(name = "teacher_reg_number")
    private String teacherRegNumber;
    @Size(max = 15)
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    public JpaManualTransactionView() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedByAppUserId() {
        return createdByAppUserId;
    }

    public void setCreatedByAppUserId(String createdByAppUserId) {
        this.createdByAppUserId = createdByAppUserId;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDateClaimed() {
        return dateClaimed;
    }

    public void setDateClaimed(Date dateClaimed) {
        this.dateClaimed = dateClaimed;
    }

    public Boolean getClaimed() {
        return claimed;
    }

    public void setClaimed(Boolean claimed) {
        this.claimed = claimed;
    }

    public Boolean getCreditedAccount() {
        return creditedAccount;
    }

    public void setCreditedAccount(Boolean creditedAccount) {
        this.creditedAccount = creditedAccount;
    }

    public String getProcessingMessage() {
        return processingMessage;
    }

    public void setProcessingMessage(String processingMessage) {
        this.processingMessage = processingMessage;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
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

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public Date getDateInitialized() {
        return dateInitialized;
    }

    public void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeacherRegNumber() {
        return teacherRegNumber;
    }

    public void setTeacherRegNumber(String teacherRegNumber) {
        this.teacherRegNumber = teacherRegNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
