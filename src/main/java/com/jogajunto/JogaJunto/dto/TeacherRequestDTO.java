package com.jogajunto.JogaJunto.dto;

import com.jogajunto.JogaJunto.model.auth.User;

import java.util.Date;
import java.util.UUID;

public record TeacherRequestDTO(
        String username,
        String password,
        String name,
        String email,
        String phone,
        String cpf
) {
}
