package com.acm.jwt.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
 * @since 08/06/2020
 */
@Component
public class JwtRequestFilter implements Filter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final String REFRESH = "/service/refresh";
    private static final List<String> EXTERNAL_ENDPOINTS = Arrays.asList("/service/refresh", "/authenticate",
            "/api/acm/users");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer: ")) {
            jwtToken = requestTokenHeader.substring(7);

            if (!jwtTokenUtil.isValidToken(jwtToken)) {
                throw new IOException("Invalid JWT Token");
            }

        } else {
            if (requestTokenHeader != null) {
                throw new IOException("JWT Token does not begin with Bearer");
            } else {
                System.out.println(request.getRequestURI());
                if (!EXTERNAL_ENDPOINTS.contains(request.getRequestURI())) {
                    throw new IOException("Missing JWT token");
                } else {
                    System.out.println(request.getMethod());
                    if (request.getRequestURI().contains("/api/acm/users") && !request.getMethod().equals("POST")
                            && !request.getMethod().equals("OPTIONS")) {
                        throw new IOException("Missing JWT token");
                    }
                }
            }
        }

        if (!request.getRequestURI().contains(REFRESH)) {
            chain.doFilter(request, response);
        }
    }
}