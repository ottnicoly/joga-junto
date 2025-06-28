package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.dto.FinancialResponsibleRequestDTO;
import com.jogajunto.JogaJunto.dto.StudentRequestDTO;
import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.model.FinancialResponsible;
import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.model.Teacher;
import com.jogajunto.JogaJunto.repository.ClassroomRepository;
import com.jogajunto.JogaJunto.repository.FinancialResponsibleRepository;
import com.jogajunto.JogaJunto.repository.StudentRepository;
import com.jogajunto.JogaJunto.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FinancialResponsibleRepository financialRepository;

    @Autowired
    FinancialResponsibleService financialService;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    public List<Student> getAllStudents(){
        var students = studentRepository.findAll();
        return students;
    }

    public List<Student> getStudentsByClassroomId(Integer classroomId) {
        return studentRepository.findByClassrooms_Id(classroomId);
    }

    public Student registerStudent(StudentRequestDTO data, UUID user) {
        if (studentExists(data.name(), data.birthDate(), data.cpf())) {
            System.out.println("aluno ja esta cadastrado");
            return null;
        }
        Student student = new Student();
        student.setName(data.name());
        student.setBirthDate(data.birthDate());
        student.setMotherName(data.motherName());
        student.setFatherName(data.fatherName());
        student.setAddress(data.address());
        student.setPhone(data.phone());
        student.setCpf(data.cpf());

        if (data.classroom_id() != null) {
            Classroom classroom = classroomRepository.findById(data.classroom_id())
                    .orElseThrow(() -> new RuntimeException("Classroom não encontrado"));
            student.setClassrooms(classroom);
        }

        if (data.financialResponsible() != null){
            List<FinancialResponsible> responsibles = new ArrayList<>();

            for (FinancialResponsibleRequestDTO requestDTO : data.financialResponsible()){

                FinancialResponsible responsible = financialRepository
                        .findByCpf(requestDTO.cpf())
                        .orElseGet(() -> {
                            FinancialResponsible newResponsible = new FinancialResponsible(requestDTO);
                            return financialRepository.save(newResponsible);
                        });
                responsibles.add(responsible);
            }

            student.setFinancialResponsibles(responsibles);
        }

        Teacher teacher = teacherRepository.findByUserId(user);
        student.setTeacher(teacher);

        studentRepository.save(student);
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

    @Transactional
    public Student addFinancialResponsibles(Integer studentId, List<FinancialResponsibleRequestDTO> responsiblesData) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        for (FinancialResponsibleRequestDTO dto : responsiblesData) {
            FinancialResponsible responsible = financialService.findOrCreate(dto);
            if (!student.getFinancialResponsibles().contains(responsible)) {
                student.getFinancialResponsibles().add(responsible);
            }
        }

        return studentRepository.save(student);
    }

}
