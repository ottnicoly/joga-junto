package com.jogajunto.JogaJunto.domain.classroom;

import com.jogajunto.JogaJunto.domain.student.StudentRequestDTO;
import com.jogajunto.JogaJunto.domain.teacher.Teacher;

import java.util.List;

public record RequestClassroomDTO(
        String classType,
        String classTime,
        List<DaysOfWeek> daysOfWeek,
        Teacher teacher,
        List<StudentRequestDTO> student
) {
}
