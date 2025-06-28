package com.jogajunto.JogaJunto.repository;

import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByTeacher(Teacher teacher);

    List<Student> findByClassrooms_Id(Integer classroomId);

}
