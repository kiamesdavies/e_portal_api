/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.util;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public enum AccountType {
    APP_USER, ORGANIZATION;

    /**
     * organization or appuser id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

}
