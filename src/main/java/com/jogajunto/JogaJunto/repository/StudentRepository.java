package com.jogajunto.JogaJunto.repository;

import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.model.FinancialResponsible;
import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByTeacher(Teacher teacher);

    List<Student> findByClassrooms_Id(Integer classroomId);

    List<Student> findByFinancialResponsiblesContaining(FinancialResponsible responsible);

    List<Student> findByClassrooms(Classroom classroom);

    Optional<Student> findByName(String name);

}
