package com.portal.commons.models;

import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-02-05T22:22:28.916+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaManualTransactionView"
)
public class ManualTransactionView {

    @Size(
            max = 64,
            min = 0
    )
    private String transactionId;

    @Size(
            max = 20,
            min = 0
    )
    private String registrationNumber;

    @Size(
            max = 64,
            min = 0
    )
    private String paymentId;

    private Date dateCreated;

    @Size(
            max = 64,
            min = 0
    )
    private String createdByAppUserId;

    private Double amountPaid;

    private Date dateClaimed;

    private Boolean claimed;

    private Boolean creditedAccount;

    @Size(
            max = 2147483647,
            min = 0
    )
    private String processingMessage;

    @Size(
            max = 64,
            min = 0
    )
    private String receipt;

    @Size(
            max = 128,
            min = 0
    )
    private String bankName;

    @Size(
            max = 128,
            min = 0
    )
    private String bankTeller;

    private Date datePaid;

    @Size(
            max = 64,
            min = 0
    )
    private String appUserId;

    private Date dateInitialized;

    private Date expiryDate;

    private Boolean paid;

    @Size(
            max = 64,
            min = 0
    )
    private String categoryId;

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
            min = 0
    )
    private String teacherRegNumber;

    @Size(
            max = 15,
            min = 0
    )
    private String mobileNumber;

    private Date registrationDate;

    public String getTransactionId() {
        return this.transactionId;
    }

    public Void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return null;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public Void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return null;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public Void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return null;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public Void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return null;
    }

    public String getCreatedByAppUserId() {
        return this.createdByAppUserId;
    }

    public Void setCreatedByAppUserId(String createdByAppUserId) {
        this.createdByAppUserId = createdByAppUserId;
        return null;
    }

    public Double getAmountPaid() {
        return this.amountPaid;
    }

    public Void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
        return null;
    }

    public Date getDateClaimed() {
        return this.dateClaimed;
    }

    public Void setDateClaimed(Date dateClaimed) {
        this.dateClaimed = dateClaimed;
        return null;
    }

    public Boolean getClaimed() {
        return this.claimed;
    }

    public Void setClaimed(Boolean claimed) {
        this.claimed = claimed;
        return null;
    }

    public Boolean getCreditedAccount() {
        return this.creditedAccount;
    }

    public Void setCreditedAccount(Boolean creditedAccount) {
        this.creditedAccount = creditedAccount;
        return null;
    }

    public String getProcessingMessage() {
        return this.processingMessage;
    }

    public Void setProcessingMessage(String processingMessage) {
        this.processingMessage = processingMessage;
        return null;
    }

    public String getReceipt() {
        return this.receipt;
    }

    public Void setReceipt(String receipt) {
        this.receipt = receipt;
        return null;
    }

    public String getBankName() {
        return this.bankName;
    }

    public Void setBankName(String bankName) {
        this.bankName = bankName;
        return null;
    }

    public String getBankTeller() {
        return this.bankTeller;
    }

    public Void setBankTeller(String bankTeller) {
        this.bankTeller = bankTeller;
        return null;
    }

    public Date getDatePaid() {
        return this.datePaid;
    }

    public Void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
        return null;
    }

    public String getAppUserId() {
        return this.appUserId;
    }

    public Void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
        return null;
    }

    public Date getDateInitialized() {
        return this.dateInitialized;
    }

    public Void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
        return null;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public Void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return null;
    }

    public Boolean getPaid() {
        return this.paid;
    }

    public Void setPaid(Boolean paid) {
        this.paid = paid;
        return null;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public Void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getTeacherRegNumber() {
        return this.teacherRegNumber;
    }

    public Void setTeacherRegNumber(String teacherRegNumber) {
        this.teacherRegNumber = teacherRegNumber;
        return null;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public Void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return null;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public Void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return null;
    }
}
