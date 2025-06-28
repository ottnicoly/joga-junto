package com.jogajunto.JogaJunto.mapper;

import com.jogajunto.JogaJunto.dao.classroom.ClassroomDAO;
import com.jogajunto.JogaJunto.dao.classroom.StudentBasicDAO;
import com.jogajunto.JogaJunto.model.Classroom;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassroomMapper {

    public static ClassroomDAO toDAO(Classroom classroom) {
        return new ClassroomDAO(
                classroom.getId(),
                classroom.getClassType(),
                classroom.getClassTime(),
                classroom.getName(),
                classroom.getDaysOfWeek(),
                classroom.getStudents() == null ? List.of() :
                        classroom.getStudents().stream()
                                .map(s -> new StudentBasicDAO(s.getId(), s.getName()))
                                .toList()
        );
    }

    public static List<ClassroomDAO> toDAOList(List<Classroom> classrooms) {
        return classrooms.stream()
                .map(ClassroomMapper::toDAO)
                .toList();
    }
}
