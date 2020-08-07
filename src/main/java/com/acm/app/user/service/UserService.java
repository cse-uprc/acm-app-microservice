package com.acm.app.user.service;

import java.util.List;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.dao.UserDAO;
import com.acm.library.globals.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserService sends request to userDAO
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
@Component
public class UserService {

	@Autowired
	private UserDAO userDAO;

	/**
	 * Service method for getting data for a user
	 *
	 * @param request - requested user for lookup
	 * @return user from DAO
	 */
	public List<User> getUser(UserGetRequest request) {
		return userDAO.getUser(request);
	}

	public User getUserCredentials(UserGetRequest request) throws UserNotFoundException {
		return userDAO.getUserCredentials(request);
	}

	/**
	 * Create a new user, passed to the DAO.
	 *
	 * @param user to be created
	 * @return user response from DAO
	 */
	public User createNewUser(User user) {
		return userDAO.createNewUser(user);
	}
}