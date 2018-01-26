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
        String trackingId = System.getenv("UA-78412537-1");
        final RestTemplate restTemplate = new RestTemplate();

        URIBuilder builder = new URIBuilder();
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
            URI results = restTemplate.postForLocation(uri.getPath(), String.class);
            System.out.println(results);
        } catch (URISyntaxException e) {
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
    public void enviarVisitaAPagina(Integer anonymousClientId, String url, String title) {
        final RestTemplate restTemplate = new RestTemplate();
        String trackingId = "UA-78412537-1";
        String anonymousUserId = String.valueOf(anonymousClientId);
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("www.google-analytics.com").setPath("/collect").addParameter("v", "1")
                .addParameter("tid", trackingId).addParameter("cid", anonymousUserId).addParameter("t", "pageview")
                .addParameter("dh", "momoko.es").addParameter("dp", url) // Event category.
                .addParameter("dt", title); // Event action.
        URI uri = null;

        try {
            uri = builder.build();
            URI results = restTemplate.postForLocation(uri.getPath(), String.class);
            System.out.println(results);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
