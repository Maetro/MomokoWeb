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

    /**
     * Instantiates a new momoko configuracion.
     */
    public MomokoConfiguracion() {
        System.out.println("Creando la configuracion");
    }

    /**
     * Gets the momoko configuracion.
     *
     * @return the momoko configuracion
     */
    public Map<String, Configuracion> getMomokoConfiguracion() {
        return this.momokoConfiguracion;
    }

    /**
     * The Class Configuracion.
     */
    public static class Configuracion {

        /** The url files. */
        private String urlFiles;

        /** The url images. */
        private String urlImages;

        /** The url upload. */
        private String urlUpload;

        /** The test. */
        private String test;

        /**
         * Gets the url files.
         *
         * @return the url files
         */
        public String getUrlFiles() {
            return this.urlFiles;
        }

        /**
         * Sets the url files.
         *
         * @param urlFiles
         *            the new url files
         */
        public void setUrlFiles(final String urlFiles) {
            this.urlFiles = urlFiles;
        }

        /**
         * Gets the url images.
         *
         * @return the url images
         */
        public String getUrlImages() {
            return this.urlImages;
        }

        /**
         * Sets the url images.
         *
         * @param urlImages
         *            the new url images
         */
        public void setUrlImages(final String urlImages) {
            this.urlImages = urlImages;
        }

        /**
         * Gets the url upload.
         *
         * @return the url upload
         */
        public String getUrlUpload() {
            return this.urlUpload;
        }

        /**
         * Sets the url upload.
         *
         * @param urlUpload
         *            the new url upload
         */
        public void setUrlUpload(final String urlUpload) {
            this.urlUpload = urlUpload;
        }

        /**
         * Gets the test.
         *
         * @return the test
         */
        public String getTest() {
            return this.test;
        }

        /**
         * Sets the test.
         *
         * @param test
         *            the new test
         */
        public void setTest(final String test) {
            this.test = test;
        }

    }

}
