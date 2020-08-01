package com.acm.app.user.dao;

import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.app.user.client.domain.User;

import com.acm.service.sql.SQLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static org.springframework.http.RequestEntity.post;

/**
 * Used to manage queries about users from the database
 *
 * @author Kiyle Winborne
 * @since July 27, 2020
 */
@Component
public class UserDAO {

	@Autowired
	private SQLBuilder sqlBuilder;

	/**
	 *
	 * @param user user object to add to database
	 * @return user from database
	 */
	public User createUser(User user)
	{
		sqlBuilder.setQueryFile("userDAO");
		//sqlBuilder.setParams(params);
		//post(sqlBuilder.getSql("createUser"));
		return user;
	}

	public User getUser(UserGetRequest request) {
		return null;
	}

	public User getUsername(UserGetRequest request)
	{
		return null;
	}

}
