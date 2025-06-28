package com.jogajunto.JogaJunto.dto;

import com.jogajunto.JogaJunto.model.enums.DaysOfWeek;

import java.util.List;

public record ClassroomRequestDTO(
        String name,
        String classTime,
        String classType,
        List<DaysOfWeek> daysOfWeek
) {
}
