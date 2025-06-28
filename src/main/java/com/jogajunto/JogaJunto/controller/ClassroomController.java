package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.dto.ClassroomRequestDTO;
import com.jogajunto.JogaJunto.dto.RequestClassroomDTO;
import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.model.Teacher;
import com.jogajunto.JogaJunto.repository.ClassroomRepository;
import com.jogajunto.JogaJunto.repository.TeacherRepository;
import com.jogajunto.JogaJunto.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    ClassroomService service;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    public ResponseEntity<?> listClassrooms(JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getToken().getClaimAsString("sub"));
        Teacher teacher = teacherRepository.findByUserId(userId);
        var classrooms = classroomRepository.findByTeacher(teacher);
        return ResponseEntity.ok(classrooms);
    }

    @PostMapping
    public ResponseEntity<?> createClassroom(@RequestBody ClassroomRequestDTO data,
                                             JwtAuthenticationToken token) {
        String userIdStr = token.getToken().getClaimAsString("sub");
        UUID userId = UUID.fromString(userIdStr);

        Teacher teacher = teacherRepository.findByUserId(userId);

        Classroom classroom = new Classroom();
        classroom.setClassTime(data.classTime());
        classroom.setClassType(data.classType());
        classroom.setTeacher(teacher);

        classroomRepository.save(classroom);
        return ResponseEntity.ok().build();
    }


}