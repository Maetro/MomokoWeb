/**
 * AuthorizationServerApplication.java 14-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthorizationServerApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}