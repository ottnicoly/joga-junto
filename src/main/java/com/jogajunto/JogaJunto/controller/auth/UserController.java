package com.jogajunto.JogaJunto.controller.auth;

import com.jogajunto.JogaJunto.dto.CreateUserRequest;
import com.jogajunto.JogaJunto.dto.TeacherRequestDTO;
import com.jogajunto.JogaJunto.model.auth.User;
import com.jogajunto.JogaJunto.service.auth.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserRequest dto){

        userService.newUser(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> getUser(){

        List<User> users = userService.getUser();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody TeacherRequestDTO dto) {
        userService.registerTeacherWithUser(dto);
        return ResponseEntity.status(201).build();
    }

}
