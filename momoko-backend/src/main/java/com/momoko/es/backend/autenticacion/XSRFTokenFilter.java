/**
 * XSRFTokenFilter.java 16-oct-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.backend.autenticacion;

public class XSRFTokenFilter {// extends OncePerRequestFilter {
    //
    // @Override
    // protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
    // final FilterChain filterChain) throws ServletException, IOException {
    // final CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    // if (csrf != null) {
    // Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
    // final String token = csrf.getToken();
    // if ((cookie == null) || ((token != null) && !token.equals(cookie.getValue()))) {
    // cookie = new Cookie("XSRF-TOKEN", token);
    // cookie.setPath("/");
    // response.addCookie(cookie);
    // }
    // }
    // filterChain.doFilter(request, response);
    // }
}