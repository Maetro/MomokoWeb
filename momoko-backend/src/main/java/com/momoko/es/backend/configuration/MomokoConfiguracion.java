/**
 * MomokoConfiguracion.java 05-sep-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The Class MomokoConfiguracion.
 *
 * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
 */
@Configuration
@ConfigurationProperties("momokoConfiguracion")
public class MomokoConfiguracion {

    /** The es servidor local. */
    private boolean esServidorLocal;

    /** The directorios. */
    private Directorios directorios = new Directorios();

    /**
     * Checks if is es servidor local.
     *
     * @return true, if is es servidor local
     */
    public boolean isEsServidorLocal() {
        return this.esServidorLocal;
    }

    /**
     * Sets the es servidor local.
     *
     * @param esServidorLocal
     *            the new es servidor local
     */
    public void setEsServidorLocal(final boolean esServidorLocal) {
        this.esServidorLocal = esServidorLocal;
    }

    /**
     * Gets the directorios.
     *
     * @return the directorios
     */
    public Directorios getDirectorios() {
        return this.directorios;
    }

    /**
     * Sets the directorios.
     *
     * @param directorios
     *            the new directorios
     */
    public void setDirectorios(final Directorios directorios) {
        this.directorios = directorios;
    }

    /**
     * The Class Directorios.
     *
     * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
     */
    public static class Directorios {
        private Remote remote = new Remote();
        private Local local = new Local();

        /**
         * Gets the local.
         *
         * @return the local
         */
        public Local getLocal() {
            return this.local;
        }

        /**
         * Sets the local.
         *
         * @param local
         *            the new local
         */
        public void setLocal(final Local local) {
            this.local = local;
        }

        /**
         * Gets the remote.
         *
         * @return the remote
         */
        public Remote getRemote() {
            return this.remote;
        }

        /**
         * Sets the remote.
         *
         * @param remote
         *            the new remote
         */
        public void setRemote(final Remote remote) {
            this.remote = remote;
        }

    }

    /**
     * The Class Configuracion.
     *
     * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
     */
    public static class Local {

        /** The url files. */
        private String urlFiles;

        /** The url images. */
        private String urlImages;

        /** The url upload. */
        private String urlUpload;

        /** The url sitemap. */
        private String urlSitemap;

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
         * Gets the url sitemap.
         *
         * @return the url sitemap
         */
        public String getUrlSitemap() {
            return this.urlSitemap;
        }

        /**
         * Sets the url sitemap.
         *
         * @param urlSitemap
         *            the new url sitemap
         */
        public void setUrlSitemap(final String urlSitemap) {
            this.urlSitemap = urlSitemap;
        }

    }

    /**
     * The Class Configuracion.
     *
     * @author <a href="RMaetro@gmail.com">Ramon Casares</a>
     */
    public static class Remote {

        /** The url files. */
        private String urlFiles;

        /** The url images. */
        private String urlImages;

        /** The url upload. */
        private String urlUpload;

        /** The url sitemap. */
        private String urlSitemap;

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
         * Gets the url sitemap.
         *
         * @return the url sitemap
         */
        public String getUrlSitemap() {
            return this.urlSitemap;
        }

        /**
         * Sets the url sitemap.
         *
         * @param urlSitemap
         *            the new url sitemap
         */
        public void setUrlSitemap(final String urlSitemap) {
            this.urlSitemap = urlSitemap;
        }

    }

}
