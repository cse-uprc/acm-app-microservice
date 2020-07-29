package com.acm.app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.dao.UserDAO;

@Component
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public User getUser(int ID) {
		return userDAO.getUser(ID);
	}
}