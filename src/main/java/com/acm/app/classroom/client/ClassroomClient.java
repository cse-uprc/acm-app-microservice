package com.acm.app.classroom.client;

import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.classroom.client.domain.Instructor;
import com.acm.app.classroom.client.domain.Session;
import com.acm.app.classroom.rest.ClassroomController;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles outside requests for classroom data
 *
 * @author Sam Butler
 * @since August 26, 2020
 */
public class ClassroomClient {

    @Autowired
    private ClassroomController classroomController;

    /**
     * Client method to create a new classroom
     * 
     * @param classroom - The classroom to be created
     * @return {@link Classroom} object
     */
    public Classroom createClassroom(Classroom classroom) {
        return classroomController.createClassroom(classroom);
    }

    /**
     * Client method to associate an instructor with a classroom
     * 
     * @param instructor - The instructor to associate with the classroom
     * @return {@link Instructor} object
     */
    public Instructor createClassroomInstructor(Instructor instructor) {
        return classroomController.createClassroomInstructor(instructor);
    }

    /**
     * Client method to associate a session time with a classroom
     * 
     * @param session - The session to be associated with a classroom
     * @return {@link Session} object
     */
    public Session createClassroomSession(Session session) {
        return classroomController.createClassroomSession(session);
    }
}
