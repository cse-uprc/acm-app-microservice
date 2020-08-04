package com.acm.app.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.acm.app.user.client.domain.User;

/**
 * formats the data returned from the DAO in manner that we want.
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
@Component
public class UserCredentialMapper implements RowMapper<User> {
	@Autowired
	public static UserCredentialMapper USER_CREDENTIAL_MAPPER = new UserCredentialMapper();

	/**
	 * Mapper for the user object returned from the database
	 *
	 * @param rs     - set of results requested
	 * @param rowNum - row number of the user or set of users
	 * @return user data from database
	 * @throws SQLException - if something goes wrong
	 */
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setUserId(rs.getInt("user_id"));
		return user;
	}
}
