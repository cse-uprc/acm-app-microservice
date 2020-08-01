package com.acm.app.user.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.rest.UserController;

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
}
