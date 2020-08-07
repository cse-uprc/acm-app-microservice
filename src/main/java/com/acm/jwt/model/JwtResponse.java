package com.acm.jwt.model;

import java.io.Serializable;

/**
 * JWT Response model.
 *
 * @author SethHancock
 * @since August 1, 2020
 */
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
