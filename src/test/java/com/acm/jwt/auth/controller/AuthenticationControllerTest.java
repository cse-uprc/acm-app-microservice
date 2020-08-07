package com.acm.jwt.auth.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import com.acm.app.user.client.domain.User;
import com.acm.jwt.auth.service.AuthenticationService;
import com.acm.jwt.config.JwtTokenUtil;
import com.acm.jwt.model.JwtRequest;
import com.acm.library.service.PasswordHash;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authController;

    @Mock
    private AuthenticationService authService;

    @Spy
    private JwtTokenUtil jwtUtil;

    @BeforeEach
    public void setUpTests() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetToken() throws Exception {
        User testUser = new User();

        testUser.setUsername("test");
        testUser.setPassword(PasswordHash.hashPassword("password"));
        testUser.setFirstName("test");
        testUser.setLastName("user");
        testUser.setEmail("user@user.useruseruser");

        when(authService.verifyUser("test", "password")).thenReturn(testUser);

        ResponseEntity<?> response = authController.createAuthToken(new JwtRequest("test", "password"));

        ObjectMapper objMapper = new ObjectMapper();
        Map<String, String> mappedObject = objMapper.convertValue(response.getBody(),
                new TypeReference<Map<String, String>>() {
                });

        assertNotNull("Token is not null", response);
        assertEquals("test", jwtUtil.getUsernameFromToken(mappedObject.get("token")));
    }

}