package com.portal.commons.models;

import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.Size;

@Generated(
    date = "2018-02-05T22:22:28.973+0100",
    comments = "By Akinniranye James Ayodele",
    value = "Generated from com.portal.entities.JpaOnlineTransactionView"
)
public class OnlineTransactionView {
  @Size(
      max = 64,
      min = 0
  )
  private String transactionId;

  @Size(
      max = 64,
      min = 0
  )
  private String paymentId;

  private Date dateInitialized;

  private Double amountToPay;

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

  @Size(
      max = 64,
      min = 0
  )
  private String rrr;

  @Size(
      max = 64,
      min = 0
  )
  private String appUserId;

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
    this.transactionId= transactionId;
    return null;
  }

  public String getPaymentId() {
    return this.paymentId;
  }

  public Void setPaymentId(String paymentId) {
    this.paymentId= paymentId;
    return null;
  }

  public Date getDateInitialized() {
    return this.dateInitialized;
  }

  public Void setDateInitialized(Date dateInitialized) {
    this.dateInitialized= dateInitialized;
    return null;
  }

  public Double getAmountToPay() {
    return this.amountToPay;
  }

  public Void setAmountToPay(Double amountToPay) {
    this.amountToPay= amountToPay;
    return null;
  }

  public Double getAmountPaid() {
    return this.amountPaid;
  }

  public Void setAmountPaid(Double amountPaid) {
    this.amountPaid= amountPaid;
    return null;
  }

  public Date getDatePayed() {
    return this.datePayed;
  }

  public Void setDatePayed(Date datePayed) {
    this.datePayed= datePayed;
    return null;
  }

  public Boolean getSucessful() {
    return this.sucessful;
  }

  public Void setSucessful(Boolean sucessful) {
    this.sucessful= sucessful;
    return null;
  }

  public Boolean getCreditedAccount() {
    return this.creditedAccount;
  }

  public Void setCreditedAccount(Boolean creditedAccount) {
    this.creditedAccount= creditedAccount;
    return null;
  }

  public String getResponseMessage() {
    return this.responseMessage;
  }

  public Void setResponseMessage(String responseMessage) {
    this.responseMessage= responseMessage;
    return null;
  }

  public String getResponseCode() {
    return this.responseCode;
  }

  public Void setResponseCode(String responseCode) {
    this.responseCode= responseCode;
    return null;
  }

  public String getRrr() {
    return this.rrr;
  }

  public Void setRrr(String rrr) {
    this.rrr= rrr;
    return null;
  }

  public String getAppUserId() {
    return this.appUserId;
  }

  public Void setAppUserId(String appUserId) {
    this.appUserId= appUserId;
    return null;
  }

  public Date getExpiryDate() {
    return this.expiryDate;
  }

  public Void setExpiryDate(Date expiryDate) {
    this.expiryDate= expiryDate;
    return null;
  }

  public Boolean getPaid() {
    return this.paid;
  }

  public Void setPaid(Boolean paid) {
    this.paid= paid;
    return null;
  }

  public String getCategoryId() {
    return this.categoryId;
  }

  public Void setCategoryId(String categoryId) {
    this.categoryId= categoryId;
    return null;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public Void setFirstName(String firstName) {
    this.firstName= firstName;
    return null;
  }

  public String getLastName() {
    return this.lastName;
  }

  public Void setLastName(String lastName) {
    this.lastName= lastName;
    return null;
  }

  public String getTeacherRegNumber() {
    return this.teacherRegNumber;
  }

  public Void setTeacherRegNumber(String teacherRegNumber) {
    this.teacherRegNumber= teacherRegNumber;
    return null;
  }

  public String getMobileNumber() {
    return this.mobileNumber;
  }

  public Void setMobileNumber(String mobileNumber) {
    this.mobileNumber= mobileNumber;
    return null;
  }

  public Date getRegistrationDate() {
    return this.registrationDate;
  }

  public Void setRegistrationDate(Date registrationDate) {
    this.registrationDate= registrationDate;
    return null;
  }
}
