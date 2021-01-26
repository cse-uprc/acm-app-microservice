package com.acm.service.auth.service;

import com.acm.app.user.client.UserClient;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.library.globals.exceptions.InvalidPasswordException;
import com.acm.library.service.PasswordHash;
import com.google.common.collect.Sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Authorization Service takes a user request and checks the values entered for
 * credentials against known values in the database. If correct credentials are
 * passed, it will grant access to the user requested.
 *
 * @author Kiyle Winborne
 * @since 8/2/2020
 */
@Service
public class AuthenticationService {
    @Autowired
    private UserClient userClient;

    /**
     * Verifies user credentials passed as a JWTRequest
     *
     * @param username - Entered username at login.
     * @param password - Password entered at login.
     * @return requestedUser - the user which matches the credentials above.
     * @throws Exception - Throw an exception if the credentials do not match.
     */
    public User verifyUser(String username, String password) throws Exception {
        UserGetRequest request = new UserGetRequest();
        request.setUsername(Sets.newHashSet(username));
        User requestedUser = userClient.getUsers(request).get(0);
        if (!PasswordHash.checkPassword(password, requestedUser.getPassword())) {
            throw new InvalidPasswordException();
        }
        return requestedUser;
    }
}
