/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaPayment.findAll", query = "SELECT j FROM JpaPayment j")})
public class JpaPayment implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_type")
    private String paymentType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_to_pay")
    private double amountToPay;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaCategory categoryId;

    @Size(max = 128)
    @Column(name = "certificate_path")
    private String certificatePath;

    @Size(max = 128)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 128)
    @Column(name = "bank_teller")
    private String bankTeller;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "payment_id")
    private String paymentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_initialized")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInitialized;
    @Column(name = "date_paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paid")
    private boolean paid;
    @Column(name = "amount_paid")
    private Double amountPaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentId", fetch = FetchType.LAZY)
    private List<JpaOnlineTransaction> jpaOnlineTransactionList;
    @OneToMany(mappedBy = "paymentId", fetch = FetchType.LAZY)
    private List<JpaManualTransaction> jpaManualTransactionList;
    @JoinColumn(name = "app_user_id", referencedColumnName = "app_user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JpaAppUser appUserId;

    public JpaPayment() {
    }

    public JpaPayment(String paymentId) {
        this.paymentId = paymentId;
    }

    public JpaPayment(String paymentId, Date dateInitialized, boolean paid, double amountToPay, String paymentType) {
        this.paymentId = paymentId;
        this.dateInitialized = dateInitialized;
        this.paid = paid;
        this.amountToPay = amountToPay;
        this.paymentType = paymentType;
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

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @XmlTransient
    public List<JpaOnlineTransaction> getJpaOnlineTransactionList() {
        return jpaOnlineTransactionList;
    }

    public void setJpaOnlineTransactionList(List<JpaOnlineTransaction> jpaOnlineTransactionList) {
        this.jpaOnlineTransactionList = jpaOnlineTransactionList;
    }

    @XmlTransient
    public List<JpaManualTransaction> getJpaManualTransactionList() {
        return jpaManualTransactionList;
    }

    public void setJpaManualTransactionList(List<JpaManualTransaction> jpaManualTransactionList) {
        this.jpaManualTransactionList = jpaManualTransactionList;
    }

    public JpaAppUser getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(JpaAppUser appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaPayment)) {
            return false;
        }
        JpaPayment other = (JpaPayment) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaPayment[ paymentId=" + paymentId + " ]";
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

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

   
    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public JpaCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(JpaCategory categoryId) {
        this.categoryId = categoryId;
    }

}
