package com.acm.app.user.dao;

import static com.acm.app.user.mapper.UserMapper.USER_MAPPER;
import static com.acm.service.sql.SqlClient.getPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.service.sql.SQLBuilder;

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
}
