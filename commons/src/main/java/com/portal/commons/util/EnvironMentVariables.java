/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.util;

import java.util.Optional;

/**
 *
 * @author Akinniranye James Ayodele
 */
public class EnvironMentVariables {

    public static final String API_SERVER_URL = Optional.ofNullable(System.getenv("API_SERVER_URL")).orElse("http://192.168.8.100:9000");
    public static final String API_MAIN_DOMAIN = Optional.ofNullable(System.getenv("API_MAIN_DOMAIN")).orElse("localhost");
    public static final String APP_SERVER_URL = Optional.ofNullable(System.getenv("APP_SERVER_URL")).orElse("http://localhost:9090/main.html");
//http://enketo.tattara.com/j

    public static final String ENKETO_SERVER_URL = Optional.ofNullable(System.getenv("ENKETO_SERVER_URL")).orElse("http://localhost:8097");

    public static final String ENKETO_SERVER_TOKEN = Optional.ofNullable(System.getenv("ENKETO_SERVER_TOKEN")).orElse("Basic RUVSVElVQ0pTSERHS0hEMjM0MzI1Og==");
    public static final String COOKIE_SERVER_URL = Optional.ofNullable(System.getenv("COOKIE_SERVER_URL")).orElse("localhost");

    //this is not the original check the docker compose file to see original
    public static final String AUTHENTICATION_TOKEN = Optional.ofNullable(System.getenv("AUTHENTICATION_TOKEN")).orElse("653637265733b6579736563726573457365637265733b657973656372657345");

    public static final String REMITTA_MERCHANTID = Optional.ofNullable(System.getenv("REMITTA_MERCHANTID")).orElse("2547916");
    public static final String REMITTA_SERVICETYPEID = Optional.ofNullable(System.getenv("REMITTA_SERVICETYPEID")).orElse("4430731");
    public static final String REMITTA_APIKEY = Optional.ofNullable(System.getenv("REMITTA_APIKEY")).orElse("1946");
    public static final String REMITTA_GATEWAYURL = Optional.ofNullable(System.getenv("REMITTA_GATEWAYURL")).orElse("http://remitademo.net/remita/ecomm/init.reg");
    public static final String REMITTA_CHECKSTATUSURL = Optional.ofNullable(System.getenv("REMITTA_CHECKSTATUSURL")).orElse("http://remitademo.net/remita/ecomm");
    public static final String REMITTA_RESPONSE_URL = API_SERVER_URL + "/remitta/instant_callback/?orderId=%s";

    public static final String  STORAGE_PATH = Optional.ofNullable(System.getenv("STORAGE_PATH")).orElse("/var/portal/storage/");
    
    //EMAIL
    public static final String MAIL_HOST = Optional.ofNullable(System.getenv("MAIL_HOST")).orElse("email-smtp.us-west-2.amazonaws.com");
    public static final String MAIL_PORT = Optional.ofNullable(System.getenv("MAIL_PORT")).orElse("465");
    public static final String MAIL_USER = Optional.ofNullable(System.getenv("MAIL_USER")).orElse("AKIAJFUKJIX3ZQ5X5ZKA");
    public static final String MAIL_PASSWORD = Optional.ofNullable(System.getenv("MAIL_PASSWORD")).orElse("AvizI3PnsbgfZQv4g+Hhs/vQu0U3RAa+HzCiAzu25lX8");
    public static final String MAIL_DEFAULT_SENDER_ADDRESS = Optional.ofNullable(System.getenv("MAIL_DEFAULT_SENDER_ADDRESS")).orElse("no-reply@tattara.com");
    public static final String MAIL_DEFAULT_SENDER_NAME = Optional.ofNullable(System.getenv("MAIL_DEFAULT_SENDER_NAME")).orElse("Tattara");

}
