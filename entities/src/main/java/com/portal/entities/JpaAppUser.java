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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "app_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaAppUser.findAll", query = "SELECT j FROM JpaAppUser j")})
public class JpaAppUser implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUserId", fetch = FetchType.LAZY)
    private List<JpaPinRequest> jpaPinRequestList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "app_user_id")
    private String appUserId;
    @Size(max = 64)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 64)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "password")
    private String password;
    @Size(max = 15)
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Size(max = 64)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdByAppUserId", fetch = FetchType.LAZY)
    private List<JpaManualTransaction> jpaManualTransactionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUserId", fetch = FetchType.LAZY)
    private List<JpaPayment> jpaPaymentList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jpaAppUser", fetch = FetchType.LAZY)
    private JpaApplicationData jpaApplicationData;

    public JpaAppUser() {
    }

    public JpaAppUser(String appUserId) {
        this.appUserId = appUserId;
    }

    public JpaAppUser(String appUserId, String userName, String password, Date dateCreated, String createdBy, String roleName) {
        this.appUserId = appUserId;
        this.userName = userName;
        this.password = password;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.roleName = roleName;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<JpaManualTransaction> getJpaManualTransactionList() {
        return jpaManualTransactionList;
    }

    public void setJpaManualTransactionList(List<JpaManualTransaction> jpaManualTransactionList) {
        this.jpaManualTransactionList = jpaManualTransactionList;
    }

    @XmlTransient
    public List<JpaPayment> getJpaPaymentList() {
        return jpaPaymentList;
    }

    public void setJpaPaymentList(List<JpaPayment> jpaPaymentList) {
        this.jpaPaymentList = jpaPaymentList;
    }

    public JpaApplicationData getJpaApplicationData() {
        return jpaApplicationData;
    }

    public void setJpaApplicationData(JpaApplicationData jpaApplicationData) {
        this.jpaApplicationData = jpaApplicationData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appUserId != null ? appUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaAppUser)) {
            return false;
        }
        JpaAppUser other = (JpaAppUser) object;
        if ((this.appUserId == null && other.appUserId != null) || (this.appUserId != null && !this.appUserId.equals(other.appUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaAppUser[ appUserId=" + appUserId + " ]";
    }

    @XmlTransient
    public List<JpaPinRequest> getJpaPinRequestList() {
        return jpaPinRequestList;
    }

    public void setJpaPinRequestList(List<JpaPinRequest> jpaPinRequestList) {
        this.jpaPinRequestList = jpaPinRequestList;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
