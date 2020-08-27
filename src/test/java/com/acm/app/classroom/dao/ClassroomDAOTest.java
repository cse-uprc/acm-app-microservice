package com.acm.app.classroom.dao;

import static com.acm.app.classroom.testData.ClassroomTestFactory.buildClassroomPostObject;
import static com.acm.app.classroom.testData.ClassroomTestFactory.defaultInstructor;
import static com.acm.app.classroom.testData.ClassroomTestFactory.defaultSession;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.acm.app.classroom.client.domain.Campus;
import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.classroom.client.domain.Days;
import com.acm.app.classroom.client.domain.Instructor;
import com.acm.app.classroom.client.domain.Session;
import com.acm.jwt.utility.JwtHolder;
import com.acm.service.sql.SQLBuilder;
import com.acm.service.sql.SqlClient;
import com.google.common.collect.Sets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassroomDAOTest {

    @InjectMocks
    private ClassroomDAO dao;

    @Mock
    private SqlClient sqlClient;

    @Mock
    private JwtHolder jwtHolder;

    @Mock
    private SQLBuilder sqlBuilder;

    @Captor
    private ArgumentCaptor<Map<String, Set<?>>> paramCaptor;

    @BeforeEach
    public void setUp() {
        initMocks(this);

        when(jwtHolder.getRequiredUserId()).thenReturn(1200);
        when(sqlBuilder.getSql(anyString())).thenReturn("TestString");
    }

    @Test
    public void testCreateClassroom() {
        Classroom newClass = buildClassroomPostObject();

        when(sqlClient.post(anyString())).thenReturn(Optional.of(1));

        Classroom response = dao.createClassroom(newClass);

        verify(sqlBuilder).setParams(paramCaptor.capture());

        assertEquals("Name of class should be Calculus 3", Sets.newHashSet("Calculus 3"),
                paramCaptor.getValue().get("name"));
        assertEquals("Campus should be RIDGE", Sets.newHashSet(Campus.RIDGE), paramCaptor.getValue().get("campusType"));
        assertEquals("Room Id should be 200", Sets.newHashSet(200), paramCaptor.getValue().get("roomId"));
        assertEquals("Insert User should be 1200", Sets.newHashSet(1200), paramCaptor.getValue().get("insertUserId"));

        assertEquals("Class Id should be 1", 1, response.getId());
        assertEquals("Occupant Count should be 1", 1, response.getOccupantCount());
        assertEquals("Insert user id should be 1200", 1200, response.getInsertUserId());
    }

    @Test
    public void testCreateClassroomInstructor() {
        Instructor instructor = defaultInstructor();
        instructor.setClassId(1);

        when(sqlClient.post(anyString())).thenReturn(Optional.of(-1));

        Instructor response = dao.createClassroomInstructor(instructor);

        verify(sqlBuilder).setParams(paramCaptor.capture());

        assertEquals("Class id should be set to 1", Sets.newHashSet(1), paramCaptor.getValue().get("classId"));
        assertEquals("First name should be set to Test", Sets.newHashSet("Test"),
                paramCaptor.getValue().get("firstName"));
        assertEquals("Last Name should be set to Teacher", Sets.newHashSet("Teacher"),
                paramCaptor.getValue().get("lastName"));

        assertEquals("Class Id should be 1", 1, response.getClassId());
        assertEquals("Instructor first name should be Test", "Test", response.getFirstName());
        assertEquals("Instructor last name should be Teacher", "Teacher", response.getLastName());
    }

    @Test
    public void testCreateClassroomSession() {
        Session session = defaultSession();
        session.setClassId(1);

        when(sqlClient.post(anyString())).thenReturn(Optional.of(-1));

        Session response = dao.createClassroomSession(session);

        verify(sqlBuilder).setParams(paramCaptor.capture());

        assertEquals("Class Id should be 1", Sets.newHashSet(1), paramCaptor.getValue().get("classId"));
        assertEquals("Start Time param should be 12:00", Sets.newHashSet("12:00"),
                paramCaptor.getValue().get("startTime"));
        assertEquals("End Time param should be 13:00", Sets.newHashSet("13:00"), paramCaptor.getValue().get("endTime"));

        assertEquals("Class Id should be 1", 1, response.getClassId());
        assertEquals("Start Time param should be 12:00", "12:00", response.getStartTime());
        assertEquals("End Time param should be 13:00", "13:00", response.getEndTime());
        assertEquals("Meeting days should be MON, WED, FRI", Sets.newHashSet(Days.MON, Days.WED, Days.FRI),
                response.getMeetingDays());
    }
}