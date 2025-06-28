package com.jogajunto.JogaJunto.dao.student;

public record StudentSimpleDAO(
        Integer id,
        String name,
        String motherName,
        String fatherName,
        String phone,
        String cpf
) {
}
