/**
 * ClientApplication.java 18-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@EnableAutoConfiguration
@Configuration
@RestController
public class ClientApplication {

    @RequestMapping("/")
    public String home(final Principal user) {
        return "Hello " + user.getName();
    }

    public static void main(final String[] args) {
        new SpringApplicationBuilder(ClientApplication.class).properties("spring.config.name=client").run(args);
    }

}
