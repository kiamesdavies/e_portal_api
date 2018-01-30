/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author poseidon
 */
@Embeddable
public class JpaPinRequestPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "pin_id")
    private String pinId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "app_user_id")
    private String appUserId;

    public JpaPinRequestPK() {
    }

    public JpaPinRequestPK(String pinId, String appUserId) {
        this.pinId = pinId;
        this.appUserId = appUserId;
    }

    public String getPinId() {
        return pinId;
    }

    public void setPinId(String pinId) {
        this.pinId = pinId;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pinId != null ? pinId.hashCode() : 0);
        hash += (appUserId != null ? appUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaPinRequestPK)) {
            return false;
        }
        JpaPinRequestPK other = (JpaPinRequestPK) object;
        if ((this.pinId == null && other.pinId != null) || (this.pinId != null && !this.pinId.equals(other.pinId))) {
            return false;
        }
        if ((this.appUserId == null && other.appUserId != null) || (this.appUserId != null && !this.appUserId.equals(other.appUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaPinRequestPK[ pinId=" + pinId + ", appUserId=" + appUserId + " ]";
    }
    
}
