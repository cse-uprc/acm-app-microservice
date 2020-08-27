package com.acm.app.classroom.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.classroom.client.domain.Instructor;
import com.acm.app.classroom.client.domain.Session;
import com.acm.app.classroom.service.ClassroomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classroom controller to manager incoming endpoints.
 *
 * @author Sam Butler
 * @since August 26, 2020
 */
@CrossOrigin
@RestController
@RequestMapping("api/acm/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    /**
     * Endpoint to create a new classroom
     *
     * @param newClass - The newClass object to be created
     * @return {@link Classroom} object
     */
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Classroom createClassroom(@RequestBody Classroom newClass) {
        return classroomService.createClassroom(newClass);
    }

    /**
     * Endpoint to associate an instructor with a classroom
     *
     * @param newClass - The instructor to be associated
     * @return {@link Instructor} object
     */
    @PostMapping(value = "/instructor", produces = APPLICATION_JSON_VALUE)
    public Instructor createClassroomInstructor(@RequestBody Instructor instructor) {
        return classroomService.createClassroomInstructor(instructor);
    }

    /**
     * Endpoint to associate a session with a classroom
     *
     * @param session - The session to be associated
     * @return {@link Session} object
     */
    @PostMapping(value = "/session", produces = APPLICATION_JSON_VALUE)
    public Session createClassroomSession(@RequestBody Session session) {
        return classroomService.createClassroomSession(session);
    }
}
