/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.user_management.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author poseidon
 */
public class Login {

    @Size(
            max = 64,
            min = 1
    )
    @NotNull
    private String userName;

    @Size(
            max = 128,
            min = 1
    )
    @NotNull
    private String password;

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

}
