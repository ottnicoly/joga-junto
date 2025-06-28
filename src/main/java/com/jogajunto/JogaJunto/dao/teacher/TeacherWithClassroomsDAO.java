package com.jogajunto.JogaJunto.dao.teacher;

import java.util.List;

public record TeacherWithClassroomsDAO(
        Integer id,
        String name,
        List<ClassroomSimpleDAO> classrooms
) {
}
