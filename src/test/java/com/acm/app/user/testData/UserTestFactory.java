package com.acm.app.user.testData;

import com.acm.app.classroom.client.domain.Classroom;
import com.acm.app.user.client.domain.User;

/**
 * Test Data class to store common info and data for test
 * 
 * @author Sam Butler
 * @since August 27, 2020
 */
public class UserTestFactory {

    /**
     * Helper method to build a test user object
     * 
     * @return {@link Classroom} object
     */
    public static User defaultUser() {
        User user = new User();

        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("jelqwizardMaximus@gmail.com");

        return user;
    }
}