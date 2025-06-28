package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.dao.student.StudentDAO;
import com.jogajunto.JogaJunto.dao.student.StudentDetailsDAO;
import com.jogajunto.JogaJunto.dao.student.StudentSimpleDAO;
import com.jogajunto.JogaJunto.dto.AddResponsiblesRequestDTO;
import com.jogajunto.JogaJunto.dto.StudentRequestDTO;
import com.jogajunto.JogaJunto.mapper.StudentMapper;
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
    public ResponseEntity<List<StudentDAO>> getAllStudents(JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getToken().getSubject());
        Teacher teacher = teacherRepository.findByUserId(userId);

        var students = studentRepository.findByTeacher(teacher);
        var daos = students.stream()
                .map(StudentMapper::toDAO)
                .toList();

        return ResponseEntity.ok(daos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDetailsDAO> getStudentDetails(@PathVariable Integer id) {
        var details = studentService.getStudentDetails(id);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/byClassroom/{classroomId}")
    public ResponseEntity<List<StudentSimpleDAO>> getStudentsByClassroom(@PathVariable Integer classroomId) {
        List<Student> students = studentService.getStudentsByClassroomId(classroomId);

        List<StudentSimpleDAO> daos = students.stream()
                .map(StudentMapper::toSimpleDAO)
                .toList();

        return ResponseEntity.ok(daos);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}