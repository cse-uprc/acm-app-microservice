package com.acm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/acm")
@Controller
public class ACMController {

	/**
	 * End point to create a manager based on the given request
	 * 
	 * @param manager - manager details to create
	 * @return Manager Detail Object {@link ManagerDetail}
	 * @since July 11, 2020
	 */
	@GetMapping()
	public String getACMEndpoint() {
		return "ACM Endpoint Working!";
	}
}
