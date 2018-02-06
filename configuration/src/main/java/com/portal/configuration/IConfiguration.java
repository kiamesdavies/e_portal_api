/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.configuration;

import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppConfig;

import java.util.List;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public interface IConfiguration {

    static enum Config {

        TOKEN_EXPIRY_SECONDS,
        PASSWORD_RECOVERY_EXPIRY_SECONDS,
        DEFAULT_ROLE,
        RENEWAL_NOTIFICATION_DATES,
        PIN_EXPIRY_SECONDS,
        
    }

    String getConfiguration(Config configKey) throws ResourceNotFound;

    List<AppConfig> getAppConfigs();

}
