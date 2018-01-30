/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.models;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class AppConfig {

    @NotNull
    @Size(min = 1, max = 64)
    private String appConfigId;
    private boolean isAvailableToUser;
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String appConfigValue;

    private boolean isCheck;
    private String possibleValues;

    private boolean isDefault = true;
    private String appConfigName;
    private String appConfigDescription;

    public AppConfig() {
    }

    public AppConfig(String appConfigId) {
        this.appConfigId = appConfigId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.appConfigId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AppConfig other = (AppConfig) obj;
        if (!Objects.equals(this.appConfigId, other.appConfigId)) {
            return false;
        }
        return true;
    }

    public String getAppConfigId() {
        return appConfigId;
    }

    public void setAppConfigId(String appConfigId) {
        this.appConfigId = appConfigId;
    }

    public boolean isIsAvailableToUser() {
        return isAvailableToUser;
    }

    public void setIsAvailableToUser(boolean isAvailableToUser) {
        this.isAvailableToUser = isAvailableToUser;
    }

    public String getAppConfigValue() {
        return appConfigValue;
    }

    public void setAppConfigValue(String appConfigValue) {
        this.appConfigValue = appConfigValue;
    }

    public boolean isIsCheck() {
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

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getAppConfigName() {
        return appConfigName;
    }

    public void setAppConfigName(String appConfigName) {
        this.appConfigName = appConfigName;
    }

    public String getAppConfigDescription() {
        return appConfigDescription;
    }

    public void setAppConfigDescription(String appConfigDescription) {
        this.appConfigDescription = appConfigDescription;
    }

}
