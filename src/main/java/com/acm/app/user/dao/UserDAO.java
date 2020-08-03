package com.acm.app.user.dao;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.service.exceptions.UserNotFoundException;
import com.acm.service.sql.SQLBuilder;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.acm.app.user.mapper.UserCredentialMapper.USER_CREDENTIAL_MAPPER;
import static com.acm.app.user.mapper.UserMapper.USER_MAPPER;
import static com.acm.service.sql.SqlClient.getPage;
import static com.acm.service.sql.SqlClient.getTemplate;

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
	public List<User> getUser(UserGetRequest request)
	{
		Map<String, Set<?>> params = new HashMap<>();

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);

		return getPage(sqlBuilder.getSql("getUsers"), USER_MAPPER);
	}
	
	public User getUserCredentials(UserGetRequest request) throws UserNotFoundException
	{
		Map<String, Set<?>> params = new HashMap<>();
		
		if(request.getId() != 0) {
			params.put("userId", Sets.newHashSet(request.getId()));
		} else {
			params.put("username", Sets.newHashSet(request.getUsername()));
			if(request.getUsername() == null)
			{
				throw new UserNotFoundException("User not found at:", request.getId());
			}
		}

		sqlBuilder.setQueryFile("userDAO");
		sqlBuilder.setParams(params);
	

		return getTemplate(sqlBuilder.getSql("getUserCredentials"), USER_CREDENTIAL_MAPPER);
	}
}
