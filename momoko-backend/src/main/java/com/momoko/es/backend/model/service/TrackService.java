/**
 * TrackService.java 26-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service;

import java.util.Map;

public interface TrackService {

    /**
     * Enviar visita a pagina.
     *
     * @param clientId
     *            the client id
     * @param url
     *            the url
     * @param title
     *            the title
     */
    public void enviarVisitaAPagina(String clientId, String url, String title);

    /**
     * Enviar visita a pagina.
     *
     * @param string
     *            the string
     * @param allRequestParams
     *            the all request params
     */
    public void enviarVisitaAPagina(String urlObjetivo, Map<String, String> allRequestParams);

    public void alamacenarVisitaBD();

}
