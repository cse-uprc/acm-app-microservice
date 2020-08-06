package com.acm.app.user.rest;

import java.util.List;

import com.acm.app.mail.client.MailClient;
import com.acm.app.mail.client.domain.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.service.UserService;
import com.acm.library.globals.exceptions.UserNotFoundException;

import javax.mail.MessagingException;

/**
 * passes data to UserService from the client-side
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
@CrossOrigin
@RestController
@RequestMapping("api/acm/users")
@Controller
public class UserController
{

	@Autowired
	private UserService userService;
	@Autowired
	private MailClient mailClient;

	/**
	 * Get a user profile based on the given user get request.
	 *
	 * @param request - requested user for lookup
	 * @return user - gets user object from userService
	 */
	@GetMapping()
	public List<User> getUser(UserGetRequest request) {
		return userService.getUser(request);
	}

	@GetMapping("/credentials")
	public User getUserCredentials(UserGetRequest request) throws UserNotFoundException {
		return userService.getUserCredentials(request);
	}

	/**
	 * Pass a create user request to the userService
	 *
	 * @param user - the user to be created
	 * @return user response from userService
	 */
	@PostMapping()
	public User createNewUser(@RequestBody User user){
		return userService.createNewUser(user);
	}
}


