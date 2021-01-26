package com.acm.app.user.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * passes data to UserService from the client-side
 *
 * @author Kiyle Winborne
 * @since July 30, 2020
 */
@CrossOrigin()
@RestController
@RequestMapping("api/acm/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Get a user profile based on the given user get request.
	 *
	 * @param request - requested user for lookup
	 * @return user - gets user object from userService
	 */
	@GetMapping()
	public List<User> getUsers(UserGetRequest request) {
		return userService.getUsers(request);
	}

	/**
	 * Pass a create user request to the userService and emails the admins to notify
	 * them.
	 *
	 * @param user - the user to be created
	 * @return user response from userService
	 */
	@GetMapping(value = "/dim-user", produces = APPLICATION_JSON_VALUE)
	public List<User> getDimUsers(UserGetRequest request) {
		return userService.getDimUsers(request);
	}

	/**
	 * Creates a password for the given user based on the JWT token of the user,
	 * only ADMINS can create a verified user
	 * 
	 * @param user - The user object containing the password to hash and store
	 * @return - the user that has been updated down the chain.
	 * @throws Exception
	 */
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public User createVerifiedUser(@RequestBody User user) throws Exception {
		return userService.createVerifiedUser(user);
	}

	/**
	 * Pass a create user request to the userService and emails the admins to notify
	 * them.
	 *
	 * @param user - the user to be created
	 * @return user response from userService
	 */
	@PostMapping(value = "/dim-user", produces = APPLICATION_JSON_VALUE)
	public User createDimUser(@RequestBody User user) throws Exception {
		return userService.createDimUser(user);
	}

	/**
	 * Creates a password for the given user based on the JWT token of the user
	 * 
	 * @param user - The user object containing the password to hash and store
	 * @return - the user that has been updated down the chain.
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping(value = "/create-password", produces = APPLICATION_JSON_VALUE)
	public User createUserPassword(@RequestBody User user) throws NoSuchAlgorithmException {
		return userService.createUserPassword(user.getPassword());
	}

	/**
	 * Approve or Disapprove dim user from dim table
	 * 
	 * @param user - The user object containing the password to hash and store
	 * @return - the user that has been updated down the chain.
	 * @throws Exception
	 */
	@PostMapping(value = "/dim-user/{id}/validate/{status}", produces = APPLICATION_JSON_VALUE)
	public User validateDimUser(@PathVariable("id") int dimUserId, @PathVariable("status") Boolean approved)
			throws Exception {
		return userService.validateDimUser(dimUserId, approved);
	}

	/**
	 * Allows the userClient to update a user, by passing it to the user Service
	 * 
	 * @param user - the user to be updated
	 * @return - the user that has been updated down the chain.
	 */
	@PutMapping(produces = APPLICATION_JSON_VALUE)
	public User updateUser(User user) {
		return userService.updateUser(user);
	}

	/**
	 * Delete user from dim table
	 * 
	 * @param user - The user object containing the password to hash and store
	 * @return - the user that has been updated down the chain.
	 * @throws Exception
	 */
	@DeleteMapping(value = "/dim-user/{id}")
	public void deleteDimUser(@PathVariable("id") int dimUserId) throws Exception {
		userService.deleteDimUser(dimUserId);
	}
}
