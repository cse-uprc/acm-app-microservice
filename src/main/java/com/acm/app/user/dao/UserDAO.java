package com.acm.app.user.dao;

import static com.acm.app.user.mapper.UserCredentialMapper.USER_CREDENTIAL_MAPPER;
import static com.acm.app.user.mapper.UserMapper.USER_MAPPER;
import static com.acm.service.sql.SqlClient.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.library.globals.exceptions.UserNotFoundException;
import com.acm.service.sql.SQLBuilder;
import com.google.common.collect.Sets;

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
	 * Pull user information based on user get request
	 *
	 * @param request {@link UserGetRequest} object to search upon
	 * @return List of {@link User}
	 */
	public List<User> getUser(UserGetRequest request) {
		Map<String, Set<?>> params = new HashMap<>();

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);

		return getPage(sqlBuilder.getSql("getUsers"), USER_MAPPER);
	}

	public User getUserCredentials(UserGetRequest request) throws UserNotFoundException {
		Map<String, Set<?>> params = new HashMap<>();

		if (request.getId() != 0) {
			params.put("userId", Sets.newHashSet(request.getId()));
		} else {
			params.put("username", Sets.newHashSet(request.getUsername()));
			if (request.getUsername() == null) {
				throw new UserNotFoundException("User not found at:", request.getId());
			}
		}

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);

		return getTemplate(sqlBuilder.getSql("getUserCredentials"), USER_CREDENTIAL_MAPPER);
	}

	public User createNewUser(User newUser) {
		Map<String, Set<?>> params = new HashMap<>();

		params.put("firstName", Sets.newHashSet(newUser.getFirstName()));
		params.put("lastName", Sets.newHashSet(newUser.getLastName()));
		params.put("email", Sets.newHashSet(newUser.getEmail()));

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);

		post(sqlBuilder.getSql("createUser"));

		return newUser;
	}
	}
