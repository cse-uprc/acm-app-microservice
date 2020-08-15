package com.acm.app.user.client.domain.request;

import java.util.Set;

import com.acm.library.globals.enums.WebRole;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class handles lookups passed to the DAO.
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
public class UserGetRequest {

    @ApiModelProperty("Ids of users to filter on.")
    private Set<Integer> userId;

    @ApiModelProperty("Usernames to filter on.")
    private Set<String> username;

    @ApiModelProperty("Web Roles of a user to filter on.")
    private Set<WebRole> webRole;

    public Set<Integer> getUserId() {
        return userId;
    }

    public void setUserId(Set<Integer> userId) {
        this.userId = userId;
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
}
