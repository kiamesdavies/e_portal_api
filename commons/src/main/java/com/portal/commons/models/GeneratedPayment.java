package com.portal.admin.models;

import com.portal.commons.models.AppUser;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.246+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaPayment"
)
public class GeneratedPayment {

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
    private double amountPaid;

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

    public double getAmountPaid() {
        return this.amountPaid;
    }

    public Void setAmountPaid(double amountPaid) {
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
}
