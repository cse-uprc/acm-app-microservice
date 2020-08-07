package com.acm.jwt.auth.controller;

import com.acm.app.user.client.domain.User;
import com.acm.jwt.auth.service.AuthenticationService;
import com.acm.jwt.config.JwtTokenUtil;
import com.acm.jwt.model.JwtRequest;
import com.acm.jwt.model.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Generates a JWT after being passed a request
 *
 * @author Kiyle Winborne
 * @since 8/3/2020
 */
@CrossOrigin
@RestController
@Controller
public class AuthenticationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationService auth;

    /**
     * Generates a JWT token from a request
     *
     * @param authenticationRequest - JWT request. A username and password.
     * @return a new JWT.
     * @throws Exception - if authentication request does not match a user.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        User user = auth.verifyUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));

    }

}
