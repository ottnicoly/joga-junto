package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.domain.student.StudentRequestDTO;
import com.jogajunto.JogaJunto.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    private ResponseEntity getAllStudents(){
        service.getAllStudents();
        return ResponseEntity.ok("ok");
    }

    @PostMapping
    private ResponseEntity registerStudent(@RequestBody @Validated StudentRequestDTO data){
        service.registerStudent(data);
        return ResponseEntity.ok().build();
    }

}