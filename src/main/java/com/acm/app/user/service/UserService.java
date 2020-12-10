package com.acm.app.user.service;

import java.util.List;

import com.acm.app.mail.client.MailClient;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.dao.UserDAO;
import com.acm.library.globals.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService sends request to userDAO
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MailClient mailClient;

	/**
	 * Service method for getting data for a user
	 *
	 * @param request - requested user for lookup
	 * @return user from DAO
	 * @throws UserNotFoundException
	 */
	public List<User> getUsers(UserGetRequest request) throws UserNotFoundException {
		return userDAO.getUsers(request);
	}

	/**
	 * Create a new user, passed to the DAO.
	 *
	 * @param user to be created
	 * @return user response from DAO
	 * @throws Exception
	 */
	public User createNewUser(User user) throws Exception {
		User createdUser = userDAO.createNewUser(user);
		mailClient.notifyAdminsNewUser(createdUser);
		return createdUser;
	}

	/**
	 * Updates a users information by passing it to the DAO.
	 * 
	 * @param user - user to be modified
	 * @return - the user that has been modified.
	 */
	public User updateUser(User user) {
		return userDAO.updateUser(user);
	}
}