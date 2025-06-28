package com.jogajunto.JogaJunto.dto;

import com.jogajunto.JogaJunto.model.enums.DaysOfWeek;

import java.util.List;

public record RequestClassroomDTO(
        String classType,
        String classTime,
        List<DaysOfWeek> daysOfWeek
//        Teacher teacher
) {
}
