package com.acm.app.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("api/acm/users")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public User getUser(int ID) {
		return userService.getUser(ID);
	}

	@GetMapping()
	public String testGet() {
		return "Test Method";
	}
}
