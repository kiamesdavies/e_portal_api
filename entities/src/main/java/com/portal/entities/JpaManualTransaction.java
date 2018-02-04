/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "manual_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaManualTransaction.findAll", query = "SELECT j FROM JpaManualTransaction j")})
public class JpaManualTransaction implements Serializable {

    @Size(max = 128)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 128)
    @Column(name = "bank_teller")
    private String bankTeller;
    @Column(name = "date_paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;

    @Size( max = 64)
    @Column(name = "receipt")
    private String receipt;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "transaction_id")
    private String transactionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "registration_number")
    private String registrationNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "date_claimed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClaimed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "claimed")
    private boolean claimed;
    @Column(name = "credited_account")
    private Boolean creditedAccount;
    @Size(max = 2147483647)
    @Column(name = "processing_message")
    private String processingMessage;
    @JoinColumn(name = "created_by_app_user_id", referencedColumnName = "app_user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaAppUser createdByAppUserId;
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private JpaPayment paymentId;

    public JpaManualTransaction() {
    }

    public JpaManualTransaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public JpaManualTransaction(String transactionId, String registrationNumber, Date dateCreated, boolean claimed) {
        this.transactionId = transactionId;
        this.registrationNumber = registrationNumber;
        this.dateCreated = dateCreated;
        this.claimed = claimed;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public boolean getClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
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

    public JpaAppUser getCreatedByAppUserId() {
        return createdByAppUserId;
    }

    public void setCreatedByAppUserId(JpaAppUser createdByAppUserId) {
        this.createdByAppUserId = createdByAppUserId;
    }

    public JpaPayment getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(JpaPayment paymentId) {
        this.paymentId = paymentId;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaManualTransaction)) {
            return false;
        }
        JpaManualTransaction other = (JpaManualTransaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaManualTransaction[ transactionId=" + transactionId + " ]";
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
