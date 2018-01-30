package com.portal.admin.models;

import com.portal.commons.models.AppUser;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
    date = "2018-01-30T11:51:55.324+0100",
    comments = "By Akinniranye James Ayodele",
    value = "Generated from com.portal.entities.JpaManualTransaction"
)
public class GeneratedManualTransaction {
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

  private GeneratedPayment paymentId;

  public String getTransactionId() {
    return this.transactionId;
  }

  public Void setTransactionId(String transactionId) {
    this.transactionId= transactionId;
    return null;
  }

  public String getRegistrationNumber() {
    return this.registrationNumber;
  }

  public Void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber= registrationNumber;
    return null;
  }

  public Date getDateCreated() {
    return this.dateCreated;
  }

  public Void setDateCreated(Date dateCreated) {
    this.dateCreated= dateCreated;
    return null;
  }

  public Double getAmountPaid() {
    return this.amountPaid;
  }

  public Void setAmountPaid(Double amountPaid) {
    this.amountPaid= amountPaid;
    return null;
  }

  public Date getDateClaimed() {
    return this.dateClaimed;
  }

  public Void setDateClaimed(Date dateClaimed) {
    this.dateClaimed= dateClaimed;
    return null;
  }

  public boolean getClaimed() {
    return this.claimed;
  }

  public Void setClaimed(boolean claimed) {
    this.claimed= claimed;
    return null;
  }

  public Boolean getCreditedAccount() {
    return this.creditedAccount;
  }

  public Void setCreditedAccount(Boolean creditedAccount) {
    this.creditedAccount= creditedAccount;
    return null;
  }

  public String getProcessingMessage() {
    return this.processingMessage;
  }

  public Void setProcessingMessage(String processingMessage) {
    this.processingMessage= processingMessage;
    return null;
  }

  public AppUser getCreatedByAppUserId() {
    return this.createdByAppUserId;
  }

  public Void setCreatedByAppUserId(AppUser createdByAppUserId) {
    this.createdByAppUserId= createdByAppUserId;
    return null;
  }

  public GeneratedPayment getPaymentId() {
    return this.paymentId;
  }

  public Void setPaymentId(GeneratedPayment paymentId) {
    this.paymentId= paymentId;
    return null;
  }
}
