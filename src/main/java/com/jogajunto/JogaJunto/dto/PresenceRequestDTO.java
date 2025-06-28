package com.jogajunto.JogaJunto.dto;

import java.time.LocalDate;

public record PresenceRequestDTO(
        Integer id,
        Boolean presence,
        LocalDate date,
        Integer studentId,
        Integer classroomId
) {
}