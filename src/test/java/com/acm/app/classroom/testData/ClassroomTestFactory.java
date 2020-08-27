package com.acm.app.classroom.testData;

import java.util.Date;

import com.acm.app.classroom.client.domain.Campus;
import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.classroom.client.domain.Days;
import com.acm.app.classroom.client.domain.Instructor;
import com.acm.app.classroom.client.domain.Session;
import com.google.common.collect.Sets;

/**
 * Test Data class to store common info and data for test
 * 
 * @author Sam Butler
 * @since August 27, 2020
 */
public class ClassroomTestFactory {

    /**
     * Helper method to build a test classroom object
     * 
     * @return {@link Classroom} object
     */
    public static Classroom buildClassroomPostObject() {
        Classroom newClass = new Classroom();

        newClass.setName("Calculus 3");
        newClass.setRoomId(200);
        newClass.setCampusType(Campus.RIDGE);
        newClass.setInsertUserId(1200);
        newClass.setInstructor(defaultInstructor());
        newClass.setSession(defaultSession());

        return newClass;
    }

    /**
     * Helper method to build a test classroom object after a response is recieved
     * 
     * @return {@link Classroom} object
     */
    public static Classroom buildClassroomResponseObject() {
        Classroom response = buildClassroomPostObject();
        response.setId(3);
        response.getSession().setClassId(3);
        response.getInstructor().setClassId(3);
        response.setOccupantCount(1);
        response.setInsertDate(new Date());
        return response;
    }

    /**
     * Helper method to build a test Instructor
     * 
     * @return {@link Instructor} object
     */
    public static Instructor defaultInstructor() {
        Instructor instructor = new Instructor();

        instructor.setFirstName("Test");
        instructor.setLastName("Teacher");

        return instructor;
    }

    /**
     * Helper method to build a test Session
     * 
     * @return {@link Session} object
     */
    public static Session defaultSession() {
        Session session = new Session();

        session.setStartTime("12:00");
        session.setEndTime("13:00");
        session.setMeetingDays(Sets.newHashSet(Days.MON, Days.WED, Days.FRI));

        return session;
    }

}