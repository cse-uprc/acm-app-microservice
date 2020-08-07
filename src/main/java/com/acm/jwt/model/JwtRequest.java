package com.acm.jwt.model;

import java.io.Serializable;

/**
 * JWT Request model.
 *
 * @author SethHancock
 * @since August 1, 2020
 */
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005169420L;
    private String username;
    private String password;

    // Default Contructor for JSON parsing
    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
