package com.acm.app.user.dao;

import static com.acm.app.user.mapper.UserMapper.USER_MAPPER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.library.globals.exceptions.UserNotFoundException;
import com.acm.service.sql.SQLBuilder;
import com.acm.service.sql.SqlClient;
import com.google.common.collect.Sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Used to manage queries about users from the database
 *
 * @author Kiyle Winborne
 * @since July 27, 2020
 */
@Repository
public class UserDAO {

	@Autowired
	private SQLBuilder sqlBuilder;

	@Autowired
	private SqlClient sqlClient;

	/**
	 * Pull user information based on user get request
	 *
	 * @param request {@link UserGetRequest} object to search upon
	 * @return List of {@link User}
	 * @throws UserNotFoundException
	 */
	public List<User> getUsers(UserGetRequest request) throws UserNotFoundException {
		Map<String, Set<?>> params = new HashMap<>();

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);

		if (request.getUserId() != null) {
			params.put("userId", request.getUserId());
		}
		if (request.getUsername() != null) {
			params.put("username", request.getUsername());
		}
		if (request.getWebRole() != null) {
			params.put("webRole", request.getWebRole());
		}

		return sqlClient.getPage(sqlBuilder.getSql("getUsers"), USER_MAPPER);
	}

	/**
	 * Creates user in the database from the user object
	 *
	 * @param request {@link User} object to add
	 * @return {@link User}
	 */
	public User createNewUser(User newUser) {
		Map<String, Set<?>> params = new HashMap<>();

		params.put("firstName", Sets.newHashSet(newUser.getFirstName()));
		params.put("lastName", Sets.newHashSet(newUser.getLastName()));
		params.put("email", Sets.newHashSet(newUser.getEmail()));

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);

		Optional<Integer> id = sqlClient.post(sqlBuilder.getSql("createUser"));

		id.ifPresent(value -> newUser.setUserId(value));
		return newUser;
	}

	/**
	 * Updates the user information based on the given user object.
	 * 
	 * @param user - the user that we are updating
	 * @return {@link User} object of the updated user
	 */
	public User updateUser(User user) {
		Map<String, Set<?>> params = new HashMap<>();
		params.put("firstName", Sets.newHashSet(user.getFirstName()));
		params.put("lastName", Sets.newHashSet(user.getLastName()));
		params.put("email", Sets.newHashSet(user.getEmail()));
		params.put("username", Sets.newHashSet(user.getUsername()));
		params.put("password", Sets.newHashSet(user.getPassword()));
		params.put("userId", Sets.newHashSet(user.getUserId()));

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);

		sqlClient.post(sqlBuilder.getSql("updateUser"));

		return user;
	}
}
