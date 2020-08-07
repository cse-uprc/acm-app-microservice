package com.acm.app.user.client.domain.request;

/**
 * This class handles lookups passed to the DAO.
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
public class UserGetRequest {

    private int id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
