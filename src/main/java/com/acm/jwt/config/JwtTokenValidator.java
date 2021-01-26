package com.acm.jwt.config;

import static com.acm.jwt.config.JwtGlobals.REFRESH;
import static com.acm.jwt.config.JwtGlobals.VOID_ENDPOINTS;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JWT token validator class
 * 
 * @author Sam butler
 * @since Dec 5, 2020
 */
@Component
public class JwtTokenValidator {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final static Logger LOGGER = Logger.getLogger(JwtTokenValidator.class.getName());

    /**
     * Confirms that the request has a valid JWT token. If it does not need a token
     * for the endpoint it will pass on through.
     * 
     * @param request - The request that is being made to the endpint
     * @return boolean saying if it is a valid token or not
     * @throws IOException
     */
    public void isValidJwt(HttpServletRequest request) throws IOException {
        final String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer: ")) {
            String jwtToken = tokenHeader.substring(7);
            LOGGER.info("Processing JWT token.");

            confirmTokenFields(jwtToken);
            if (jwtTokenUtil.isTokenExpired(jwtToken)) {
                throw new IOException("JWT Token is Expired. Please Login again.");
            }
        } else {
            if (tokenHeader != null) {
                throw new IOException("JWT Token does not begin with 'Bearer: '");
            } else {
                throw new IOException("Missing JWT token.");
            }
        }
    }

    /**
     * Checks to see if the endpoint that was called is a void endpoint or not.
     * 
     * @param URI  - URI of the endpoint that was called
     * @param type - What type of request was made (GET, POST, etc.)
     * @return boolean based on if it is a void endpoint or not
     */
    public boolean isVoidEndpoint(String URI, String type) {
        for (Map.Entry<String, List<String>> entry : VOID_ENDPOINTS.entrySet()) {
            if (entry.getKey().equals(URI)) {
                for (String endpointType : entry.getValue()) {
                    if (endpointType.equals("ANY")) {
                        return true;
                    }
                    if (endpointType.equals(type)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the requested endpoint is a client refresh
     * 
     * @param request - The request being processed
     * @return boolean confirming if it is a client refresh
     */
    public boolean isClientRefresh(HttpServletRequest request) {
        return REFRESH.equals(request.getRequestURI());
    }

    /**
     * Checks to see if it has the required fields
     * 
     * @param token - Token to confirm field claims on
     * @throws IOException
     */
    private void confirmTokenFields(String token) throws IOException {
        try {
            jwtTokenUtil.getIdFromToken(token);
            jwtTokenUtil.getExpirationDateFromToken(token);
            jwtTokenUtil.isTokenExpired(token);
        } catch (Exception e) {
            throw new IOException("Could not process JWT token.");
        }
    }
}
