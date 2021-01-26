package com.acm.app.user.service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import com.acm.app.mail.client.MailClient;
import com.acm.app.mail.client.domain.MailMessage;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.dao.UserDAO;
import com.acm.jwt.utility.JwtHolder;
import com.acm.library.globals.enums.WebRole;
import com.acm.library.service.PasswordHash;
import com.google.common.collect.Sets;

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

	@Autowired
	private JwtHolder jwtHolder;

	/**
	 * Service method for getting data for a user
	 *
	 * @param request - requested user for lookup
	 * @return user from DAO
	 */
	public List<User> getUsers(UserGetRequest request) {
		return userDAO.getUsers(request);
	}

	/**
	 * Create a new user, passed to the DAO.
	 *
	 * @param user to be created
	 * @return user response from DAO
	 * @throws Exception
	 */
	public User createVerifiedUser(User user) throws Exception {
		if (!isAdmin(user)) {
			throw new Exception("Insuffiecent Permissions for user ID: " + jwtHolder.getRequiredUserId());
		}
		User createdUser = userDAO.createVerifiedUser(user);
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

	/**
	 * Get user from dim user table
	 *
	 * @param request - requested user for lookup
	 * @return user from DAO
	 */
	public List<User> getDimUsers(UserGetRequest request) {
		return userDAO.getDimUsers(request);
	}

	/**
	 * Create a new user, passed to the DAO.
	 *
	 * @param user to be created
	 * @return user response from DAO
	 * @throws Exception
	 */
	public User createDimUser(User user) throws Exception {
		User createdUser = userDAO.createDimUser(user);
		mailClient.notifyAdminsNewUser(createdUser);
		return createdUser;
	}

	/**
	 * Creates a password for the given user based on the JWT token of the user
	 * 
	 * @param user - The user object containing the password to hash and store
	 * @return - the user that has been updated down the chain.
	 * @throws NoSuchAlgorithmException
	 */
	public User createUserPassword(String password) throws NoSuchAlgorithmException {
		String hashedPassword = PasswordHash.hashPassword(password);
		return userDAO.createUserPassword(hashedPassword);
	}

	/**
	 * Delete user from dim table
	 * 
	 * @param user - The user object containing the password to hash and store
	 * @return - the user that has been updated down the chain.
	 * @throws Exception
	 */
	public User validateDimUser(int dimUserId, Boolean approved) throws Exception {
		if (!isAdmin(jwtHolder.getWebRole())) {
			throw new Exception("Insuffiecent Permissions for user ID: " + jwtHolder.getRequiredUserId());
		}

		UserGetRequest request = new UserGetRequest();
		request.setId(Sets.newHashSet(dimUserId));

		List<User> userList = getDimUsers(request);

		if (userList.isEmpty()) {
			throw new Exception("Dim user ID does not exist: " + dimUserId);
		}

		User dimUser = userList.get(0);

		userDAO.deleteDimUser(dimUserId);
		if (approved) {
			userDAO.createVerifiedUser(dimUser);
		}

		MailMessage mail = new MailMessage();
		mail.setSubject("Your User Request has been " + (approved ? "Approved!" : "Denied!"));
		mail.setBody("This is a notification to inform you about your user request\n Status: "
				+ (approved ? "Approved!" : "Denied!"));
		mail.setRecipients(Arrays.asList(dimUser.getEmail()));

		mailClient.sendMessage(mail);

		return dimUser;
	}

	/**
	 * Delete user from dim table
	 * 
	 * @param user - The user object containing the password to hash and store
	 * @return - the user that has been updated down the chain.
	 * @throws Exception
	 */
	public void deleteDimUser(int dimUserId) throws Exception {
		if (!isAdmin(jwtHolder.getWebRole())) {
			throw new Exception("Insuffiecent Permissions for user ID: " + jwtHolder.getRequiredUserId());
		}
		userDAO.deleteDimUser(dimUserId);
	}

	/**
	 * Helper method to check to see if user is an ADMIN
	 * 
	 * @param user - User object to check admin
	 * @return Boolean
	 */
	private Boolean isAdmin(User user) {
		return WebRole.ADMIN.equals(WebRole.valueOf(jwtHolder.getWebRole()));
	}

	/**
	 * Helper method to check to see if user is an ADMIN
	 * 
	 * @param user - User object to check admin
	 * @return Boolean
	 */
	private Boolean isAdmin(String role) {
		return WebRole.ADMIN.equals(WebRole.valueOf(role));
	}
}