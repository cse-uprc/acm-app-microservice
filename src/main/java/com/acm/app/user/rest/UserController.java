package com.acm.app.user.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.service.UserService;
import com.acm.library.globals.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * passes data to UserService from the client-side
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
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
	 * @throws UserNotFoundException
	 */
	@GetMapping()
	public List<User> getUsers(UserGetRequest request) throws UserNotFoundException {
		return userService.getUsers(request);
	}

	/**
	 * Pass a create user request to the userService and emails the admins to notify
	 * them.
	 *
	 * @param user - the user to be created
	 * @return user response from userService
	 */
	@PostMapping()
	public User createNewUser(@RequestBody User user) throws Exception {
		return userService.createNewUser(user);
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

}
