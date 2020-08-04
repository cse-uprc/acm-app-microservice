package com.acm.jwt.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acm.app.user.client.UserClient;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.library.globals.exceptions.InvalidPasswordException;
import com.acm.library.service.PasswordHash;

/**
 * Authorization Service takes a user request and checks the values entered for
 * credentials against known values in the database. If correct credentials are
 * passed, it will grant access to the user requested.
 *
 * @author Kiyle Winborne
 * @since 8/2/2020
 */
@Component
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
		request.setUsername(username);
		User requestedUser = userClient.getUserCredentials(request);
		if (!PasswordHash.checkPassword(password, requestedUser.getPassword())) {
			throw new InvalidPasswordException();
		}
		return requestedUser;
	}
}
