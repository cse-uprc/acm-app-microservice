package com.acm.app.user.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import com.acm.app.user.client.domain.request.UserGetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.acm.app.user.client.domain.User;
import com.acm.app.user.rest.UserController;

@SpringBootTest
public class UserClientTest {
    @InjectMocks
    private UserClient userClient;

    @InjectMocks
    private UserGetRequest request;

    @Mock
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsers() {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");

        when(userController.getUser(Mockito.any(UserGetRequest.class))).thenReturn(user);

        User userTest = userClient.getUser(request);

        assertEquals("Users should be equal", userTest, user);
    }
}