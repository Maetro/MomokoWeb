package com.momoko.es.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momoko.es.backend.security.common.MomokoProperties;
import com.momoko.es.util.MomokoUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Log log = LogFactory.getLog(AuthenticationSuccessHandler.class);

    private ObjectMapper objectMapper;
    private MomokoService<?, ?> momokoService;
    private long defaultExpirationMillis;

    public AuthenticationSuccessHandler(ObjectMapper objectMapper, MomokoService<?, ?> momokoService, MomokoProperties properties) {

        this.objectMapper = objectMapper;
        this.momokoService = momokoService;
        this.defaultExpirationMillis = properties.getJwt().getExpirationMillis();

        log.info("Created");
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Instead of handle(request, response, authentication),
        // the statements below are introduced
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String expirationMillisStr = request.getParameter("expirationMillis");
        long expirationMillis = expirationMillisStr == null ?
                defaultExpirationMillis : Long.valueOf(expirationMillisStr);

        // get the current-user
        UserDto<?> currentUser = MomokoUtils.currentUser();

        momokoService.addAuthHeader(response, currentUser.getUsername(), expirationMillis);

        // write current-user data to the response
        response.getOutputStream().print(
                objectMapper.writeValueAsString(currentUser));

        // as done in the base class
        clearAuthenticationAttributes(request);

        log.debug("Authentication succeeded for user: " + currentUser);
    }
}
