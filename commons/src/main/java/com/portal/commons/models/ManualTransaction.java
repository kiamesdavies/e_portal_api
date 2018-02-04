package com.portal.commons.models;

import com.portal.commons.models.AppUser;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.324+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaManualTransaction"
)
public class ManualTransaction {

    private String bankName;

    private String bankTeller;

    private Date datePaid;

    private String receipt;
    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String transactionId;

    @Size(
            max = 20,
            min = 1
    )
    @NotNull
    private String registrationNumber;

    private Date dateCreated;

    private Double amountPaid;

    private Date dateClaimed;

    @NotNull
    private boolean claimed;

    private Boolean creditedAccount;

    @Size(
            max = 2147483647,
            min = 0
    )
    private String processingMessage;

    private AppUser createdByAppUserId;

    private Payment paymentId;

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

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public Void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public boolean getClaimed() {
        return this.claimed;
    }

    public Void setClaimed(boolean claimed) {
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

    public AppUser getCreatedByAppUserId() {
        return this.createdByAppUserId;
    }

    public Void setCreatedByAppUserId(AppUser createdByAppUserId) {
        this.createdByAppUserId = createdByAppUserId;
        return null;
    }

    public Payment getPaymentId() {
        return this.paymentId;
    }

    public Void setPaymentId(Payment paymentId) {
        this.paymentId = paymentId;
        return null;
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
    
    

}
