package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.domain.student.StudentRequestDTO;
import com.jogajunto.JogaJunto.domain.student.Student;
import com.jogajunto.JogaJunto.domain.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    FinancialResponsibleService financialResponsibleService;

    public List<Student> getAllStudents(){
        var students = repository.findAll();
        return students;
    }

    public Student registerStudent(StudentRequestDTO data) {
        if (studentExists(data.name(), data.birthDate(), data.cpf())) {
            System.out.println("aluno ja esta cadastrado");
            return null;
        }
        Student student = new Student(data);
        repository.save(student);
        return student;
    }

    private boolean studentExists(String name, LocalDate birthDate, String cpf) {
        var alunos = getAllStudents();
        for (Student aluno : alunos ) {
            if (name.equals(aluno.getName()) && cpf.equals(aluno.getCpf()) && birthDate.equals(aluno.getBirthDate())) {
                return true;
            }
        }
        return false;
    }



}
