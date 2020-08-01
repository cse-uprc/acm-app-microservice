package com.acm.app.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.service.UserService;

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
}
