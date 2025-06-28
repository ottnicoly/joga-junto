package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.dao.teacher.TeacherWithClassroomsDAO;
import com.jogajunto.JogaJunto.dto.TeacherRequestDTO;
import com.jogajunto.JogaJunto.model.Teacher;
import com.jogajunto.JogaJunto.repository.TeacherRepository;
import com.jogajunto.JogaJunto.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity getAllTeacher() {
        var allTeacher = repository.findAll();
        System.out.println(allTeacher);
        return ResponseEntity.ok(allTeacher);
    }

    @PostMapping
    public ResponseEntity registerTeacher(@RequestBody @Validated TeacherRequestDTO data) {
        Teacher teacher = new Teacher(data.name(), data.email(), data.phone(), data.cpf());
        repository.save(teacher);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/details")
    public ResponseEntity<TeacherWithClassroomsDAO> getTeacherDetails(@PathVariable Integer id) {
        TeacherWithClassroomsDAO teacherDetails = teacherService.getTeacherDetails(id);
        return ResponseEntity.ok(teacherDetails);
    }

}
