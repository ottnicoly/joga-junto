package com.jogajunto.JogaJunto.repository;

import com.jogajunto.JogaJunto.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUserId(UUID userId);
}
