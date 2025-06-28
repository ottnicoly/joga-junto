package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.dto.AddResponsiblesRequestDTO;
import com.jogajunto.JogaJunto.dto.StudentRequestDTO;
import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.model.Teacher;
import com.jogajunto.JogaJunto.repository.StudentRepository;
import com.jogajunto.JogaJunto.repository.TeacherRepository;
import com.jogajunto.JogaJunto.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    private ResponseEntity getAllStudents(JwtAuthenticationToken token){
        UUID userId = UUID.fromString(token.getToken().getSubject());
        Teacher teacher = teacherRepository.findByUserId(userId);

        var students = studentRepository.findByTeacher(teacher);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/byClassroom/{classroomId}")
    public ResponseEntity<List<Student>> getStudentsByClassroom(@PathVariable Integer classroomId) {
        List<Student> students = studentService.getStudentsByClassroomId(classroomId);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    private ResponseEntity registerStudent(@RequestBody @Validated StudentRequestDTO data,
                                           JwtAuthenticationToken token){
        UUID userId = UUID.fromString(token.getToken().getSubject());
        studentService.registerStudent(data, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addResponsibles")
    public ResponseEntity<Student> addResponsibles(@RequestBody AddResponsiblesRequestDTO dto) {
        Student updatedStudent = studentService.addFinancialResponsibles(dto.student_id(), dto.responsibles());
        return ResponseEntity.ok(updatedStudent);
    }

}