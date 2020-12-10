package com.acm.jwt.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All Request get filtered here to confirm that it has a valid jwt token before
 * accessing data
 * 
 * @author Sam butler
 * @since Aug 6, 2020
 */
@Component
public class JwtRequestFilter implements Filter {
    private final static Logger LOGGER = Logger.getLogger(JwtRequestFilter.class.getName());

    @Autowired
    private JwtTokenValidator JWTValidator;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (JWTValidator.isVoidEndpoint(request.getRequestURI(), request.getMethod())) {
            LOGGER.info("Request is a void endpoint. No Token needed.");
            if (!JWTValidator.isClientRefresh(request)) {
                chain.doFilter(request, response);
            }
        } else {
            JWTValidator.isValidJwt(request);
            chain.doFilter(request, response);
        }
    }
}