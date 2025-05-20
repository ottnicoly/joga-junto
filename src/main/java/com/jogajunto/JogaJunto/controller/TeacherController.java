package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.domain.teacher.TeacherRequestDTO;
import com.jogajunto.JogaJunto.domain.teacher.Teacher;
import com.jogajunto.JogaJunto.domain.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository repository;

    @GetMapping
    public ResponseEntity getAllTeacher() {
        var allTeacher = repository.findAll();
        System.out.println(allTeacher);
        return ResponseEntity.ok(allTeacher);
    }

    @PostMapping
    public ResponseEntity registerTeacher(@RequestBody @Validated TeacherRequestDTO data) {
        Teacher teacher = new Teacher(data);
        repository.save(teacher);
        return ResponseEntity.ok().build();
    }

}
