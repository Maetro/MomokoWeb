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
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Configuration
@ConfigurationProperties
public class MomokoConfiguracion {

    private ImageServer momokoConfiguracion;

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
    public ImageServer getMomokoConfiguracion() {
        return this.momokoConfiguracion;
    }

    /**
     * The Class ImageServer.
     *
     * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
     */
    public static class ImageServer {
        private boolean esServidorLocal;
        private Map<String, Directorios> directorios = new HashMap<String, Directorios>();

        /**
         * Chequea si es servidor local.
         *
         * @return true, si es servidor local
         */
        public boolean isEsServidorLocal() {
            return esServidorLocal;
        }

        /**
         * Establece es servidor local.
         *
         * @param esServidorLocal
         *            nuevo es servidor local
         */
        public void setEsServidorLocal(boolean esServidorLocal) {
            this.esServidorLocal = esServidorLocal;
        }

        /**
         * Obtiene directorios.
         *
         * @return directorios
         */
        public Map<String, Directorios> getDirectorios() {
            return directorios;
        }

        /**
         * Establece directorios.
         *
         * @param directorios
         *            directorios
         */
        public void setDirectorios(Map<String, Directorios> directorios) {
            this.directorios = directorios;
        }

    }

    /**
     * The Class Directorios.
     *
     * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
     */
    public static class Directorios {
        private Configuracion configuracion;

        /**
         * Obtiene configuracion.
         *
         * @return configuracion
         */
        public Configuracion getConfiguracion() {
            return configuracion;
        }

        /**
         * Establece configuracion.
         *
         * @param configuracion
         *            nuevo configuracion
         */
        public void setConfiguracion(Configuracion configuracion) {
            this.configuracion = configuracion;
        }

    }

    /**
     * The Class Configuracion.
     *
     * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
     */
    public static class Configuracion {

        /** The url files. */
        private String urlFiles;

        /** The url images. */
        private String urlImages;

        /** The url upload. */
        private String urlUpload;


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

    }

}
