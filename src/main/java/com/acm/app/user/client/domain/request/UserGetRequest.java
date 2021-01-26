package com.acm.app.user.client.domain.request;

import java.util.Set;

import com.acm.library.globals.enums.WebRole;

/**
 * This class handles lookups passed to the DAO.
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
public class UserGetRequest {

    private Set<Integer> id;
    private Set<String> username;
    private Set<WebRole> webRole;

    public Set<Integer> getId() {
        return id;
    }

    public void setUserId(Set<Integer> id) {
        this.id = id;
    }

    public Set<String> getUsername() {
        return username;
    }

    public void setUsername(Set<String> username) {
        this.username = username;
    }

    public Set<WebRole> getWebRole() {
        return webRole;
    }

    public void setWebRole(Set<WebRole> webRole) {
        this.webRole = webRole;
    }

    public void setId(Set<Integer> id) {
        this.id = id;
    }
}
