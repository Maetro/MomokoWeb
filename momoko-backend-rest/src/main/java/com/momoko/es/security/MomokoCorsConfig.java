package com.momoko.es.security;

import com.momoko.es.commons.MomokoProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS Configuration
 */
public class MomokoCorsConfig implements WebMvcConfigurer {

	private static final Log log = LogFactory.getLog(MomokoCorsConfig.class);

	private MomokoProperties.Cors cors;
		
	public MomokoCorsConfig(MomokoProperties properties) {

		this.cors = properties.getCors();
		log.info("Created");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
        registry.addMapping("/**")
	        .allowedOrigins(cors.getAllowedOrigins())
	        .allowedMethods(cors.getAllowedMethods())
	        .allowedHeaders(cors.getAllowedHeaders())
	        .exposedHeaders(cors.getExposedHeaders())
	        .allowCredentials(true)
	        .maxAge(cors.getMaxAge());
	}
}
