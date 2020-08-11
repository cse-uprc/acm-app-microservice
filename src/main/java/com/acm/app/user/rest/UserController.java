package com.acm.app.user.rest;

import com.acm.app.mail.client.MailClient;
import com.acm.app.mail.client.domain.MailMessage;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.service.UserService;
import com.acm.library.globals.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


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
public class UserController {

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
	 * Pass a create user request to the userService and emails the admins to notify them.
	 *
	 * @param user - the user to be created
	 * @return user response from userService
	 */
	@PostMapping()
	public User createNewUser(@RequestBody User user) throws Exception {
		MailMessage message = new MailMessage();
		message.setBody("New User Account Requested for: " + user.getFirstName()+" "+user.getLastName());
		message.setSubject("ACM APP - New User Request");
		message.setRecipients(Arrays.asList("kiyleawinborne@gmail.com","sambutler1017@icloud.com","josuemvd@gmail.com", "steven.lengel@rockets.utoledo.edu"));
		mailClient.sendMessage(message);

		return userService.createNewUser(user);
	}

	/**
	 * Allows the userClient to update a user, by passing it to the user Service
	 * @param user - the user to be updated
	 * @return - the user that has been updated down the chain.
	 */
	@PutMapping(produces = APPLICATION_JSON_VALUE)
	public User updateUser(User user) {
		return userService.updateUser(user);
	}

}
