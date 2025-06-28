package com.jogajunto.JogaJunto.dao.student;

import com.jogajunto.JogaJunto.dao.teacher.ClassroomSimpleDAO;

import java.time.LocalDate;
import java.util.List;

public record StudentDetailsDAO(
        Integer id,
        String name,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        String address,
        String phone,
        String cpf,
        Double monthlyFee,
        Boolean paymentUpToDate,

        ClassroomSimpleDAO classroom,
        List<FinancialResponsibleDAO> financialResponsibles,
        List<PaymentSimpleDAO> payments,
        List<PresenceSimpleDAO> presences
) {
}
