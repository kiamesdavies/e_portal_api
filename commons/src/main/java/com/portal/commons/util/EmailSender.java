/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.util;

import com.portal.commons.events.Email;
import java.nio.charset.StandardCharsets;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class EmailSender {

    public static final String HOST = EnvironMentVariables.MAIL_HOST;
    public static final int PORT = Integer.valueOf(EnvironMentVariables.MAIL_PORT);
    public static final boolean SSL = true;
    public static final boolean TLS = true;
    public static final String USER = EnvironMentVariables.MAIL_USER;
    public static final String PASSWORD = EnvironMentVariables.MAIL_PASSWORD;
    public static final boolean DEBUG = true;

    public static void sendEmail(Email mail) throws EmailException {
        HtmlEmail htmlEmail = new HtmlEmail();

        htmlEmail.setHostName(HOST);
        htmlEmail.setSSLOnConnect(SSL);
        htmlEmail.setStartTLSEnabled(TLS);
        htmlEmail.setAuthentication(USER, PASSWORD);
        htmlEmail.setDebug(DEBUG);
        htmlEmail.setSmtpPort(PORT);

        htmlEmail.setFrom(Email.DEFAULT_SENDER_EMAIL, Email.SENDER_NAME);
        htmlEmail.addTo(mail.receipentEmail, mail.receipentName, StandardCharsets.UTF_8.name());
        htmlEmail.setSubject(mail.subject);
        htmlEmail.setHtmlMsg(mail.body);

        htmlEmail.send();

    }

}
