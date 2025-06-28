package com.jogajunto.JogaJunto.dto;

public record TeacherRequestDTO(
        String username,
        String password,
        String name,
        String email,
        String phone,
        String cpf
) {
}
