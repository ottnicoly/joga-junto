package com.jogajunto.JogaJunto.dto;

import java.time.LocalDate;
import java.util.List;

public record StudentRequestDTO(
        String name,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        String school,
        String grade,
        String address,
        String phone,
        String cpf,
        Integer classroom_id,
        List<FinancialResponsibleRequestDTO> financialResponsible
) {
}
