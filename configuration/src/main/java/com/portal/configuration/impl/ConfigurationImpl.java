/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.configuration.impl;

import com.portal.commons.exceptions.ResourceNotFound;
import com.portal.commons.models.AppConfig;
import com.portal.commons.models.GeneralMapper;
import com.portal.configuration.IConfiguration;
import com.portal.configuration.IConfiguration.Config;
import com.portal.configuration.repository.ConfigRepository;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class ConfigurationImpl implements IConfiguration {

    @Inject
    ConfigRepository configRepository;

    @Inject
    JPAApi jPAApi;

    @Override
    public String getConfiguration(Config configKey) throws ResourceNotFound {

        return this.getConfiguration(configKey.name()).getAppConfigValue();
    }

    public AppConfig getConfiguration(String configKey) throws ResourceNotFound {
        return GeneralMapper.INSTANCE.jpaAppConfigToGeneratedConfig(configRepository.getJpaAppConfig(configKey));
    }

    @Override
    public List<AppConfig> getAppConfigs() {
        return configRepository.getJpaAppConfigs().parallelStream().map(GeneralMapper.INSTANCE::jpaAppConfigToGeneratedConfig).collect(Collectors.toList());
    }

}
