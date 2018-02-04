package com.portal.commons.models;

import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.280+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaOnlineTransaction"
)
public class OnlineTransaction {

    private String rrr;
    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String transactionId;

    private Date dateInitialized;

    @NotNull
    private double amountToPay;

    private Double amountPaid;

    private Date datePayed;

    private Boolean sucessful;

    private Boolean creditedAccount;

    @Size(
            max = 256,
            min = 0
    )
    private String responseMessage;

    @Size(
            max = 10,
            min = 0
    )
    private String responseCode;

    private Payment paymentId;

    public String getTransactionId() {
        return this.transactionId;
    }

    public Void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return null;
    }

    public Date getDateInitialized() {
        return this.dateInitialized;
    }

    public Void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
        return null;
    }

    public double getAmountToPay() {
        return this.amountToPay;
    }

    public Void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
        return null;
    }

    public Double getAmountPaid() {
        return this.amountPaid;
    }

    public Void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
        return null;
    }

    public Date getDatePayed() {
        return this.datePayed;
    }

    public Void setDatePayed(Date datePayed) {
        this.datePayed = datePayed;
        return null;
    }

    public Boolean getSucessful() {
        return this.sucessful;
    }

    public Void setSucessful(Boolean sucessful) {
        this.sucessful = sucessful;
        return null;
    }

    public Boolean getCreditedAccount() {
        return this.creditedAccount;
    }

    public Void setCreditedAccount(Boolean creditedAccount) {
        this.creditedAccount = creditedAccount;
        return null;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public Void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return null;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public Void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return null;
    }

    public Payment getPaymentId() {
        return this.paymentId;
    }

    public Void setPaymentId(Payment paymentId) {
        this.paymentId = paymentId;
        return null;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }
    
    
}
