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
@Table(name = "online_transaction_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaOnlineTransactionView.findAll", query = "SELECT j FROM JpaOnlineTransactionView j")})
public class JpaOnlineTransactionView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 64)
    @Column(name = "transaction_id")
    @Id
    private String transactionId;
    @Size(max = 64)
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "date_initialized")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInitialized;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_to_pay")
    private Double amountToPay;
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "date_payed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePayed;
    @Column(name = "sucessful")
    private Boolean sucessful;
    @Column(name = "credited_account")
    private Boolean creditedAccount;
    @Size(max = 256)
    @Column(name = "response_message")
    private String responseMessage;
    @Size(max = 10)
    @Column(name = "response_code")
    private String responseCode;
    @Size(max = 64)
    @Column(name = "rrr")
    private String rrr;
    @Size(max = 64)
    @Column(name = "app_user_id")
    private String appUserId;
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

    public JpaOnlineTransactionView() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDateInitialized() {
        return dateInitialized;
    }

    public void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDatePayed() {
        return datePayed;
    }

    public void setDatePayed(Date datePayed) {
        this.datePayed = datePayed;
    }

    public Boolean getSucessful() {
        return sucessful;
    }

    public void setSucessful(Boolean sucessful) {
        this.sucessful = sucessful;
    }

    public Boolean getCreditedAccount() {
        return creditedAccount;
    }

    public void setCreditedAccount(Boolean creditedAccount) {
        this.creditedAccount = creditedAccount;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
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
