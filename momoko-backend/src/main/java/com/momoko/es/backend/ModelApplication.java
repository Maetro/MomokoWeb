/**
 * ModelApplication.java 14-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend;

import com.momoko.es.backend.model.service.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
//@EnableCaching
//@EnableAsync
//@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es" })
//@EnableAutoConfiguration
//@EnableConfigurationProperties(StorageProperties.class)
public class ModelApplication extends WebSecurityConfigurerAdapter {

    public static void main(final String[] args) {
        SpringApplication.run(ModelApplication.class, args);
    }

}