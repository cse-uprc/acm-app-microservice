package com.acm.service.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.acm.app.user.client.UserClient;
import com.acm.app.user.client.domain.User;
import com.acm.app.user.client.domain.request.UserGetRequest;
import com.acm.library.service.PasswordHash;
import com.google.common.collect.Sets;

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
        request.setUsername(Sets.newHashSet("test"));
        User confirmUser = new User();
        confirmUser.setUsername("test");
        confirmUser.setPassword(PasswordHash.hashPassword("password"));
        String password = "password";

        when(userClient.getUsers(Mockito.any())).thenReturn(Arrays.asList(confirmUser));
        User userResponse = auth.verifyUser(request.getUsername().stream().findFirst().get(), password);

        assertEquals(userResponse.getUsername(), confirmUser.getUsername());
        assertEquals(userResponse.getPassword(), confirmUser.getPassword());
    }
}