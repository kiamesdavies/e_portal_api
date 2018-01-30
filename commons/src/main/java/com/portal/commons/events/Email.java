/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.events;

import com.portal.commons.util.EnvironMentVariables;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class Email implements GenericEvents {

    public static final String DEFAULT_SENDER_EMAIL = EnvironMentVariables.MAIL_DEFAULT_SENDER_ADDRESS;

    public final String receipentEmail;
    public final String receipentName;
    public final String subject;
    public final String senderEmail;
    public final static String SENDER_NAME = EnvironMentVariables.MAIL_DEFAULT_SENDER_NAME;
    public final String body;

    public Email(String receipentEmail, String receipentName, String subject, String senderEmail, String body) {
        this.receipentEmail = receipentEmail;
        this.receipentName = receipentName;
        this.subject = subject;
        this.senderEmail = senderEmail;
        this.body = body;

    }

}
