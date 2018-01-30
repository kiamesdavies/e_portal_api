/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.configuration;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class MessageParamHolders {

    public final String name;
    public final String token;
    public final String newPassword;
    public final String invitedBy;
    public final String projectName;
    public String organizationName;
    public String receipentEmail;

    public MessageParamHolders(String name, String token) {
        this.name = name;
        this.token = token;
        this.newPassword = null;
        this.invitedBy = null;
        this.projectName = null;

    }

    public MessageParamHolders(String name, String token, String newPassword) {
        this.name = name;
        this.token = token;
        this.newPassword = newPassword;
        this.invitedBy = null;
        this.projectName = null;
    }

    public MessageParamHolders(String name, String token, String newPassword, String invitedBy) {
        this.name = name;
        this.token = token;
        this.newPassword = newPassword;
        this.invitedBy = invitedBy;
        this.projectName = null;
    }

    public MessageParamHolders(String name, String token, String newPassword, String invitedBy, String projectName) {
        this.name = name;
        this.token = token;
        this.newPassword = newPassword;
        this.invitedBy = invitedBy;
        this.projectName = projectName;
    }

}
