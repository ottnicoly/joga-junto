package com.jogajunto.JogaJunto.dao.classroom;

import com.jogajunto.JogaJunto.model.enums.DaysOfWeek;

import java.util.List;

public record ClassroomDAO(
        Integer id,
        String name,
        String classType,
        String classTime,
        List<DaysOfWeek> daysOfWeek,
        List<StudentBasicDAO> students
) {
}
