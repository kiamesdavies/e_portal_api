/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "app_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaAppConfig.findAll", query = "SELECT j FROM JpaAppConfig j")})
public class JpaAppConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "app_config_id")
    private String appConfigId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "app_config_value")
    private String appConfigValue;
    @Size(max = 256)
    @Column(name = "app_config_name")
    private String appConfigName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_available_to_user")
    private boolean isAvailableToUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_check")
    private boolean isCheck;
    @Size(max = 2147483647)
    @Column(name = "possible_values")
    private String possibleValues;
    @Size(max = 2147483647)
    @Column(name = "app_config_description")
    private String appConfigDescription;

    public JpaAppConfig() {
    }

    public JpaAppConfig(String appConfigId) {
        this.appConfigId = appConfigId;
    }

    public JpaAppConfig(String appConfigId, String appConfigValue, boolean isAvailableToUser, boolean isCheck) {
        this.appConfigId = appConfigId;
        this.appConfigValue = appConfigValue;
        this.isAvailableToUser = isAvailableToUser;
        this.isCheck = isCheck;
    }

    public String getAppConfigId() {
        return appConfigId;
    }

    public void setAppConfigId(String appConfigId) {
        this.appConfigId = appConfigId;
    }

    public String getAppConfigValue() {
        return appConfigValue;
    }

    public void setAppConfigValue(String appConfigValue) {
        this.appConfigValue = appConfigValue;
    }

    public String getAppConfigName() {
        return appConfigName;
    }

    public void setAppConfigName(String appConfigName) {
        this.appConfigName = appConfigName;
    }

    public boolean getIsAvailableToUser() {
        return isAvailableToUser;
    }

    public void setIsAvailableToUser(boolean isAvailableToUser) {
        this.isAvailableToUser = isAvailableToUser;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(String possibleValues) {
        this.possibleValues = possibleValues;
    }

    public String getAppConfigDescription() {
        return appConfigDescription;
    }

    public void setAppConfigDescription(String appConfigDescription) {
        this.appConfigDescription = appConfigDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appConfigId != null ? appConfigId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaAppConfig)) {
            return false;
        }
        JpaAppConfig other = (JpaAppConfig) object;
        if ((this.appConfigId == null && other.appConfigId != null) || (this.appConfigId != null && !this.appConfigId.equals(other.appConfigId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaAppConfig[ appConfigId=" + appConfigId + " ]";
    }
    
}
