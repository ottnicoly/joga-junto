package com.jogajunto.JogaJunto.controller.auth;

import com.jogajunto.JogaJunto.dto.auth.LoginRequest;
import com.jogajunto.JogaJunto.dto.auth.LoginResponse;
import com.jogajunto.JogaJunto.service.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = tokenService.login(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }

}
