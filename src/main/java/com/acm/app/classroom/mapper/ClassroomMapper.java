package com.acm.app.classroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.acm.app.classroom.client.domain.Classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

/**
 * Formats the data returned from the DAO
 *
 * @author Sam Butler
 * @since August 26, 2020
 */
public class ClassroomMapper implements RowMapper<Classroom> {
    @Autowired
    public final static ClassroomMapper CLASSROOM_MAPPER = new ClassroomMapper();

    /**
     * Mapper for the Classroom object returned from the database
     *
     * @param rs     - set of results requested
     * @param rowNum - row number of the user or set of users
     * @return classroom data from database
     * @throws SQLException - if something goes wrong
     */
    @Override
    public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
        Classroom classroom = new Classroom();
        return classroom;
    }
}
