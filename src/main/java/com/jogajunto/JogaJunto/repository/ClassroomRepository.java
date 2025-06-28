package com.jogajunto.JogaJunto.repository;

import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    List<Classroom> findByTeacher(Teacher teacher);
}
