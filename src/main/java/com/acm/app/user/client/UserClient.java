package com.acm.app.user.client;

import java.util.List;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.rest.UserController;
import com.acm.library.globals.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles outside requests for user data
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
@Component
public class UserClient {

	@Autowired
	private UserController userController;

	/**
	 * Returns a user from userController
	 *
	 * @param request - UserGetRequest to pass to userController.
	 */
	public List<User> getUser(UserGetRequest request) {
		return userController.getUser(request);
	}

	/**
	 * Returns user credentials from userController
	 *
	 * @param request - UserGetRequest
	 * @return {@link User} object
	 */
	public User getUserCredentials(UserGetRequest request) throws UserNotFoundException {
		return userController.getUserCredentials(request);
	}

	/**
	 * Create a new user
	 *
	 * @param user - the new user to be created
	 * @return a response from the userController
	 */

	public User createNewUser(User user) {
		return userController.createNewUser(user);
	}

}
