/**
 * TrackServiceImpl.java 26-ene-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.model.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.momoko.es.backend.model.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService {

    @Async
    public void anotarAccionAnalytics() {
        final String trackingId = System.getenv("UA-78412537-1");
        final RestTemplate restTemplate = new RestTemplate();

        final URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("www.google-analytics.com").setPath("/collect").addParameter("v", "1") // API
                                                                                                                 // Version.
                .addParameter("tid", trackingId) // Tracking ID / Property ID.
                // Anonymous Client Identifier. Ideally, this should be a UUID that
                // is associated with particular user, device, or browser instance.
                .addParameter("cid", "555").addParameter("t", "event") // Event hit type.
                .addParameter("ec", "example") // Event category.
                .addParameter("ea", "test action"); // Event action.
        URI uri = null;

        try {
            uri = builder.build();
            final URI results = restTemplate.postForLocation(uri.getPath(), String.class);
            System.out.println(results);
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }

    }

    // v=1 // Version.
    // &tid=UA-XXXXX-Y // Tracking ID / Property ID.
    // &cid=555 // Anonymous Client ID.
    // &t=pageview // Pageview hit type.
    // &dh=mydemo.com // Document hostname.
    // &dp=/home // Page.
    // &dt=homepage // Title.

    @Override
    @Async
    public void enviarVisitaAPagina(final String anonymousClientId, final String url, final String title) {
        final RestTemplate restTemplate = new RestTemplate();
        final String trackingId = "UA-78412537-1";
        final URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("www.google-analytics.com").setPath("/collect").addParameter("v", "1")
                .addParameter("tid", trackingId).addParameter("cid", anonymousClientId).addParameter("t", "pageview")
                .addParameter("dh", "momoko.es").addParameter("dp", url) // Event category.
                .addParameter("dt", title); // Event action.
        URI uri = null;

        try {
            uri = builder.build();
            final URI results = restTemplate.postForLocation(uri, String.class);
            System.out.println(anonymousClientId);
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
