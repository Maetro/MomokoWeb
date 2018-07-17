/**
 * ModelApplication.java 14-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@CrossOrigin(allowedHeaders="*",allowCredentials="true")
@SpringBootApplication
//@EnableCaching
//@EnableAsync
//@EnableOAuth2Client
//@EnableAuthorizationServer
//@Order(6)
//@CrossOrigin(origins = { "http://localhost:4200", "https://www.momoko.es" })
//@EnableAutoConfiguration
//@EnableConfigurationProperties(StorageProperties.class)
public class MomokoBackendApplication{

    public static void main(String[] args) {
        SpringApplication.run(MomokoBackendApplication.class, args);
    }

}