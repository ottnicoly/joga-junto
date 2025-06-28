package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.dao.teacher.TeacherWithClassroomsDAO;
import com.jogajunto.JogaJunto.mapper.TeacherMapper;
import com.jogajunto.JogaJunto.model.Teacher;
import com.jogajunto.JogaJunto.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherWithClassroomsDAO getTeacherDetails(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return TeacherMapper.toTeacherWithClassroomsDAO(teacher);
    }

}
