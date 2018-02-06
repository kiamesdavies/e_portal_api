/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.events;

/**
 *
 * @author poseidon
 */
public class Sms implements GenericEvents {

    public final String receipentNumber;
    public final String subject;

    public final String body;

    public Sms(String receipentNumber, String subject, String body) {
        this.receipentNumber = receipentNumber;

        this.subject = subject;

        this.body = body;

    }
}
