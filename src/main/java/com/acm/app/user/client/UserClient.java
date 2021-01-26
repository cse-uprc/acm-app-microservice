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
	 * @throws UserNotFoundException
	 */
	public List<User> getUsers(UserGetRequest request) throws UserNotFoundException {
		return userController.getUsers(request);
	}

	/**
	 * Create a new user
	 *
	 * @param user - the new user to be created
	 * @return a response from the userController
	 */

	public User createDimUser(User user) throws Exception {
		return userController.createDimUser(user);
	}

	/**
	 * Update user information
	 * 
	 * @param user - the user to be updated
	 * @return - the user that has been updated
	 */
	public User updateUser(User user) {
		return userController.updateUser(user);
	}
}
