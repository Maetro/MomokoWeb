/**
 * ClientResources.java 17-ago-2017
 *
 * Copyright 2017 INDITEX.
 * Departamento de Sistemas
 */
package com.momoko.es.backend.autenticacion;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * The Class ClientResources.
 *
 * @author <a href="josercpo@ext.inditex.com">Ramon Casares</a>
 */
public class ClientResources {
    
    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    /**
     * Obtiene client.
     *
     * @return client
     */
    public AuthorizationCodeResourceDetails getClient() {
      return client;
    }

    /**
     * Obtiene resource.
     *
     * @return resource
     */
    public ResourceServerProperties getResource() {
      return resource;
    }
}
