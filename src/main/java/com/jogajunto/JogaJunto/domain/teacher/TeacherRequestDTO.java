package com.jogajunto.JogaJunto.domain.teacher;

import java.util.Date;

public record TeacherRequestDTO(
        String name,
        Date birthDate,
        String email,
        String phone
) {
}
