package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.dao.classroom.ClassroomDAO;
import com.jogajunto.JogaJunto.dto.AddStudentDTO;
import com.jogajunto.JogaJunto.dto.ClassroomRequestDTO;
import com.jogajunto.JogaJunto.mapper.ClassroomMapper;
import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.model.Teacher;
import com.jogajunto.JogaJunto.repository.ClassroomRepository;
import com.jogajunto.JogaJunto.repository.StudentRepository;
import com.jogajunto.JogaJunto.repository.TeacherRepository;
import com.jogajunto.JogaJunto.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<?> listClassrooms(JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getToken().getClaimAsString("sub"));
        Teacher teacher = teacherRepository.findByUserId(userId);

        List<Classroom> classrooms = classroomRepository.findByTeacher(teacher);
        List<ClassroomDAO> result = ClassroomMapper.toDAOList(classrooms);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createClassroom(@RequestBody ClassroomRequestDTO data,
                                             JwtAuthenticationToken token) {
        String userIdStr = token.getToken().getClaimAsString("sub");
        UUID userId = UUID.fromString(userIdStr);

        Teacher teacher = teacherRepository.findByUserId(userId);

        Classroom classroom = new Classroom();
        classroom.setName(data.name());
        classroom.setClassTime(data.classTime());
        classroom.setClassType(data.classType());
        classroom.setDaysOfWeek(data.daysOfWeek());
        classroom.setTeacher(teacher);

        classroomRepository.save(classroom);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClassroomById(@PathVariable Integer id, JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getToken().getClaimAsString("sub"));
        Teacher teacher = teacherRepository.findByUserId(userId);

        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom not found"));

        if (!classroom.getTeacher().getId().equals(teacher.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem acesso a essa turma.");
        }

        ClassroomDAO classroomDAO = ClassroomMapper.toDAO(classroom);
        return ResponseEntity.ok(classroomDAO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable Integer id, JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getToken().getClaimAsString("sub"));
        Teacher teacher = teacherRepository.findByUserId(userId);

        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));

        if (!classroom.getTeacher().getId().equals(teacher.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para excluir esta turma.");
        }

        List<Student> students = studentRepository.findByClassrooms(classroom);
        for (Student student : students) {
            student.setClassrooms(null);
        }
        studentRepository.saveAll(students);

        classroomRepository.delete(classroom);
        return ResponseEntity.ok("Turma deletada com sucesso.");
    }

    @PutMapping("{classroomId}/add-student")
    public ResponseEntity<?> addStudentToClassroom(@PathVariable Integer classroomId,
                                                   @RequestBody AddStudentDTO data,
                                                   JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getToken().getClaimAsString("sub"));
        Teacher teacher = teacherRepository.findByUserId(userId);

        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));

        if (!classroom.getTeacher().getId().equals(teacher.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para alterar esta turma.");
        }

        Student student = studentRepository.findById(data.studentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        student.setClassrooms(classroom);
        studentRepository.save(student);

        return ResponseEntity.ok("Aluno adicionado à turma com sucesso.");
    }

}