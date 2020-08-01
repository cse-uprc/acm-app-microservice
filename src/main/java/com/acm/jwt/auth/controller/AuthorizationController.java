package com.acm.jwt.auth.controller;

import com.acm.app.user.client.domain.User;
import com.acm.jwt.auth.service.AuthorizationService;
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

@CrossOrigin
@RestController
@Controller
public class AuthorizationController
{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthorizationService auth;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authenticationRequest)
    {
        User user = auth.verifyUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));

    }

}
