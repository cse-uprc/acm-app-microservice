package com.acm.jwt.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.acm.app.user.client.UserClient;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.library.service.PasswordHash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorizationServiceTest {
    @InjectMocks
    AuthenticationService auth;
    @Mock
    UserClient userClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void verifyUser() throws Exception {
        UserGetRequest request = new UserGetRequest();
        request.setUsername("test");
        User confirmUser = new User();
        confirmUser.setUsername("test");
        confirmUser.setPassword(PasswordHash.hashPassword("password"));
        String password = "password";

        when(userClient.getUserCredentials(Mockito.any())).thenReturn(confirmUser);
        User userResponse = auth.verifyUser(request.getUsername(), password);

        assertEquals(userResponse.getUsername(), confirmUser.getUsername());
        assertEquals(userResponse.getPassword(), confirmUser.getPassword());
    }
}