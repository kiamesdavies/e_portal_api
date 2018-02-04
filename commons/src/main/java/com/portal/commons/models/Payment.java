package com.portal.commons.models;

import com.portal.commons.models.AppUser;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.246+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaPayment"
)
public class Payment {

    private double amountToPay;
    private String certificatePath;

    private String bankName;

    private String bankTeller;
    private String paymentType;
    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String paymentId;

    private Date dateInitialized;

    private Date datePaid;

    private Date expiryDate;

    @NotNull
    private boolean paid;

    @NotNull
    private Double amountPaid;

    private AppUser appUserId;

    public String getPaymentId() {
        return this.paymentId;
    }

    public Void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return null;
    }

    public Date getDateInitialized() {
        return this.dateInitialized;
    }

    public Void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
        return null;
    }

    public Date getDatePaid() {
        return this.datePaid;
    }

    public Void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
        return null;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public Void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return null;
    }

    public boolean getPaid() {
        return this.paid;
    }

    public Void setPaid(boolean paid) {
        this.paid = paid;
        return null;
    }

    public Double getAmountPaid() {
        return this.amountPaid;
    }

    public Void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
        return null;
    }

    public AppUser getAppUserId() {
        return this.appUserId;
    }

    public Void setAppUserId(AppUser appUserId) {
        this.appUserId = appUserId;
        return null;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

}
