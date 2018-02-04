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
@Table(name = "online_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaOnlineTransaction.findAll", query = "SELECT j FROM JpaOnlineTransaction j")})
public class JpaOnlineTransaction implements Serializable {

    @Size(max = 64)
    @Column(name = "rrr")
    private String rrr;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "transaction_id")
    private String transactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_initialized")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInitialized;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_to_pay")
    private double amountToPay;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaPayment paymentId;

    public JpaOnlineTransaction() {
    }

    public JpaOnlineTransaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public JpaOnlineTransaction(String transactionId, Date dateInitialized, double amountToPay) {
        this.transactionId = transactionId;
        this.dateInitialized = dateInitialized;
        this.amountToPay = amountToPay;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDateInitialized() {
        return dateInitialized;
    }

    public void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
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

    public JpaPayment getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(JpaPayment paymentId) {
        this.paymentId = paymentId;
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
        if (!(object instanceof JpaOnlineTransaction)) {
            return false;
        }
        JpaOnlineTransaction other = (JpaOnlineTransaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaOnlineTransaction[ transactionId=" + transactionId + " ]";
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }
    
}
