/**
 * MomokoConfiguracion.java 05-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The Class MomokoConfiguracion.
 */
@Configuration
@ConfigurationProperties
public class MomokoConfiguracion {

    private final Map<String, Configuracion> momokoConfiguracion = new HashMap<String, Configuracion>();

    public MomokoConfiguracion() {
        System.out.println("Creando la configuracion");
    }

    public Map<String, Configuracion> getMomokoConfiguracion() {
        return this.momokoConfiguracion;
    }

    /**
     * The Class Configuracion.
     */
    public static class Configuracion {

        private String urlFiles;
        private String test;

        public String getUrlFiles() {
            return this.urlFiles;
        }

        public void setUrlFiles(final String urlFiles) {
            this.urlFiles = urlFiles;
        }

        public String getTest() {
            return this.test;
        }

        public void setTest(final String test) {
            this.test = test;
        }

    }

}
