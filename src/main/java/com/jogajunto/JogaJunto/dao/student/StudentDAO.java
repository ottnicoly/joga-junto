package com.jogajunto.JogaJunto.dao.student;

import java.time.LocalDate;
import java.util.List;

public record StudentDAO(
        Integer id,
        String name,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        String address,
        String phone,
        String cpf,
        List<FinancialResponsibleDAO> financialResponsibles
) {
}
