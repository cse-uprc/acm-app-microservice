package com.acm.app.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.acm.app.user.client.domain.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * formats the data returned from the DAO in manner that we want.
 *
 * @author Kiyle Winborne
 * @since 7/30/2020
 */
@Component
public class DimUserMapper implements RowMapper<User> {
    public final static DimUserMapper DIM_USER_MAPPER = new DimUserMapper();

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
        user.setId(rs.getInt("dim_user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
