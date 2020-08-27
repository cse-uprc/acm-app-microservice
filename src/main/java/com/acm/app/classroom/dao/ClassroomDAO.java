package com.acm.app.classroom.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.classroom.client.domain.Instructor;
import com.acm.app.classroom.client.domain.Session;
import com.acm.jwt.utility.JwtHolder;
import com.acm.service.sql.SQLBuilder;
import com.acm.service.sql.SqlClient;
import com.google.common.collect.Sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Used to manage queries about classroom data from the database
 *
 * @author Sam Butler
 * @since August 26, 2020
 */
@Repository
public class ClassroomDAO {

    @Autowired
    private SQLBuilder sqlBuilder;

    @Autowired
    private JwtHolder jwtHolder;

    @Autowired
    private SqlClient sqlClient;

    /**
     * Dao method to create a new classroom in the database
     *
     * @param newClass - The new classroom to be created
     * @return {@link Classroom} object
     */
    public Classroom createClassroom(Classroom newClass) {
        Map<String, Set<?>> params = new HashMap<>();

        params.put("name", Sets.newHashSet(newClass.getName()));
        params.put("campusType", Sets.newHashSet(newClass.getCampusType()));
        params.put("roomId", Sets.newHashSet(newClass.getRoomId()));
        params.put("insertUserId", Sets.newHashSet(jwtHolder.getRequiredUserId()));

        sqlBuilder.setQueryFile("classroomDAO");
        sqlBuilder.setParams(params);
        Optional<Integer> id = sqlClient.post(sqlBuilder.getSql("createClassroom"));

        id.ifPresent(value -> {
            newClass.setId(value);
            newClass.getSession().setClassId(value);
            newClass.getInstructor().setClassId(value);
        });

        newClass.setInsertDate(new Date());
        newClass.setOccupantCount(1);
        newClass.setInsertUserId(jwtHolder.getRequiredUserId());

        return newClass;
    }

    /**
     * Dao method to associate a classroom with a instructor
     *
     * @param instructor - Information of the instructor
     * @return {@link Instructor} object
     */
    public Instructor createClassroomInstructor(Instructor instructor) {
        Map<String, Set<?>> params = new HashMap<>();

        params.put("classId", Sets.newHashSet(instructor.getClassId()));
        params.put("firstName", Sets.newHashSet(instructor.getFirstName()));
        params.put("lastName", Sets.newHashSet(instructor.getLastName()));

        sqlBuilder.setQueryFile("classroomDAO");
        sqlBuilder.setParams(params);
        sqlClient.post(sqlBuilder.getSql("createClassroomInstructor"));

        return instructor;
    }

    /**
     * Dao method to associate a classroom with a session
     *
     * @param instructor - Information of the instructor
     * @return {@link Session} object
     */
    public Session createClassroomSession(Session session) {
        Map<String, Set<?>> params = new HashMap<>();

        params.put("classId", Sets.newHashSet(session.getClassId()));
        params.put("startTime", Sets.newHashSet(session.getStartTime()));
        params.put("endTime", Sets.newHashSet(session.getEndTime()));
        params.put("days", Sets.newHashSet(Arrays.stream(session.getMeetingDays().toArray()).map(t -> t.toString())
                .collect(Collectors.joining(","))));

        sqlBuilder.setQueryFile("classroomDAO");
        sqlBuilder.setParams(params);
        sqlClient.post(sqlBuilder.getSql("createClassroomSession"));

        return session;
    }
}
