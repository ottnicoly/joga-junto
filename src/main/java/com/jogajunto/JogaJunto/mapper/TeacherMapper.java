package com.jogajunto.JogaJunto.mapper;


import com.jogajunto.JogaJunto.dao.teacher.ClassroomSimpleDAO;
import com.jogajunto.JogaJunto.dao.teacher.TeacherWithClassroomsDAO;
import com.jogajunto.JogaJunto.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherMapper {

    public static TeacherWithClassroomsDAO toTeacherWithClassroomsDAO(Teacher teacher) {
        List<ClassroomSimpleDAO> classrooms = teacher.getClassrooms().stream()
                .map(classroom -> new ClassroomSimpleDAO(classroom.getId(), classroom.getClassType(), classroom.getClassType()))
                .toList();

        return new TeacherWithClassroomsDAO(
                teacher.getId(),
                teacher.getName(),
                classrooms
        );
    }
}
