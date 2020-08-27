package com.acm.app.classroom.service;

import static com.acm.app.classroom.testData.ClassroomTestFactory.buildClassroomPostObject;
import static com.acm.app.classroom.testData.ClassroomTestFactory.buildClassroomResponseObject;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.classroom.client.domain.Days;
import com.acm.app.classroom.client.domain.Instructor;
import com.acm.app.classroom.client.domain.Session;
import com.acm.app.classroom.dao.ClassroomDAO;
import com.google.common.collect.Sets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassroomServiceTest {

    @InjectMocks
    private ClassroomService classroomService;

    @Mock
    private ClassroomDAO classroomDao;

    @Captor
    private ArgumentCaptor<Instructor> instructorCaptor;

    @Captor
    private ArgumentCaptor<Session> sessionCaptor;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testCreateClassroom() {
        Classroom testClass = buildClassroomPostObject();
        Classroom expectedReponse = buildClassroomResponseObject();

        when(classroomDao.createClassroom(testClass)).thenReturn(expectedReponse);
        when(classroomDao.createClassroomInstructor(any(Instructor.class))).thenReturn(expectedReponse.getInstructor());
        when(classroomDao.createClassroomSession(any(Session.class))).thenReturn(expectedReponse.getSession());

        classroomService.createClassroom(testClass);

        verify(classroomDao).createClassroom(testClass);
        verify(classroomDao).createClassroomInstructor(instructorCaptor.capture());
        verify(classroomDao).createClassroomSession(sessionCaptor.capture());

        Instructor instructorResponse = instructorCaptor.getValue();
        Session sessionResponse = sessionCaptor.getValue();

        assertEquals("Instructor class id should be 3", 3, instructorResponse.getClassId());
        assertEquals("Instructor first name should be Test", "Test", instructorResponse.getFirstName());
        assertEquals("Instructor last name should be Teacher", "Teacher", instructorResponse.getLastName());

        assertEquals("Session class id should be 3", 3, sessionResponse.getClassId());
        assertEquals("Session start time should be 12:00", "12:00", sessionResponse.getStartTime());
        assertEquals("Session end time should be 12:00", "13:00", sessionResponse.getEndTime());
        assertEquals("Session start time should be MON, WED, FRI", Sets.newHashSet(Days.MON, Days.WED, Days.FRI),
                sessionResponse.getMeetingDays());

    }
}