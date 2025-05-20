package com.jogajunto.JogaJunto.domain.student;

import com.jogajunto.JogaJunto.domain.financialresponsible.FinancialResponsibleRequestDTO;

import java.time.LocalDate;
import java.util.List;

public record StudentRequestDTO(
        String name,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        String school,
        String grade,
        Period period,
        String address,
        String phone,
        String cpf,
        List<FinancialResponsibleRequestDTO> financialResponsibles
) {
}
