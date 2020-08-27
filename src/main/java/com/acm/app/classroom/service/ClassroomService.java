package com.acm.app.classroom.service;

import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.classroom.client.domain.Instructor;
import com.acm.app.classroom.client.domain.Session;
import com.acm.app.classroom.dao.ClassroomDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classroom service to be the median between the controller and DAO
 *
 * @author Sam Butler
 * @since August 26, 2020
 */
@Service
public class ClassroomService {

    @Autowired
    private ClassroomDAO classroomDAO;

    /**
     * Service method to create a new classroom
     *
     * @param newClass - The new Classroom to be created
     * @return {@link Classroom} object
     */
    public Classroom createClassroom(Classroom newClass) {
        Classroom createdClass = classroomDAO.createClassroom(newClass);

        createClassroomInstructor(createdClass.getInstructor());
        createClassroomSession(createdClass.getSession());

        return createdClass;
    }

    /**
     * Service method to associate an instructor with a classroom
     *
     * @param instructor - The new Instructor to be created
     * @return {@link Instructor} object
     */
    public Instructor createClassroomInstructor(Instructor instructor) {
        return classroomDAO.createClassroomInstructor(instructor);
    }

    /**
     * Service method to associate an instructor with a session
     *
     * @param session - The new Session to be created
     * @return {@link Session} object
     */
    public Session createClassroomSession(Session session) {
        return classroomDAO.createClassroomSession(session);
    }
}
