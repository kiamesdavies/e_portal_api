/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.binder;

import com.github.markserrano.jsonquery.jpa.service.FilterService;
import com.github.markserrano.jsonquery.jpa.service.IFilterService;
import com.goebl.david.Webb;
import com.google.inject.AbstractModule;
import com.portal.applications.ApplicationManager;
import com.portal.configuration.IConfiguration;
import com.portal.configuration.IMessageTemplate;
import com.portal.configuration.impl.ConfigurationImpl;
import com.portal.configuration.impl.MessageTemplateImpl;
import com.portal.payment.PaymentManager;
import com.portal.user_management.AppUserManager;
import com.portal.user_management.Authentication;
import org.apache.velocity.app.VelocityEngine;

import play.libs.akka.AkkaGuiceSupport;

/**
 *
 * @author poseidon
 */
public class Module extends AbstractModule implements AkkaGuiceSupport {

    
    
    @Override
    protected void configure() {
        bind(Authentication.class);
        bind(AppUserManager.class);
        bind(IFilterService.class).to(FilterService.class);
        bind(IConfiguration.class).to(ConfigurationImpl.class);
        bind(IMessageTemplate.class).to(MessageTemplateImpl.class);
        bind(PaymentManager.class);
        bind(ApplicationManager.class);

        bind(VelocityEngine.class).toInstance(new MyVelocityEngine());

        Webb webb = Webb.create();
        bind(Webb.class).toInstance(webb);
    }

}
