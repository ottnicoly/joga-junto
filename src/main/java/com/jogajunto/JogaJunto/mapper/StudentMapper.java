package com.jogajunto.JogaJunto.mapper;

import com.jogajunto.JogaJunto.dao.student.*;
import com.jogajunto.JogaJunto.dao.teacher.ClassroomSimpleDAO;
import com.jogajunto.JogaJunto.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {

    public static StudentSimpleDAO toSimpleDAO(Student student) {
        return new StudentSimpleDAO(
                student.getId(),
                student.getName(),
                student.getMotherName(),
                student.getFatherName(),
                student.getPhone(),
                student.getCpf()
        );
    }

    public static StudentDAO toDAO(Student student) {
        List<FinancialResponsibleDAO> responsibles = student.getFinancialResponsibles()
                .stream()
                .map(StudentMapper::toDAO)
                .toList();

        return new StudentDAO(
                student.getId(),
                student.getName(),
                student.getBirthDate(),
                student.getMotherName(),
                student.getFatherName(),
                student.getAddress(),
                student.getPhone(),
                student.getCpf(),
                responsibles
        );
    }

    public static FinancialResponsibleDAO toDAO(FinancialResponsible r) {
        return new FinancialResponsibleDAO(r.getId(), r.getName(), r.getPhone(), r.getCpf());
    }

    public StudentDetailsDAO toDetailsDAO(Student student) {
        return new StudentDetailsDAO(
                student.getId(),
                student.getName(),
                student.getBirthDate(),
                student.getMotherName(),
                student.getFatherName(),
                student.getAddress(),
                student.getPhone(),
                student.getCpf(),
                student.getMonthlyFee(),
                student.isPaymentUpToDate(),

                mapClassroom(student.getClassrooms()),
                mapResponsibles(student.getFinancialResponsibles()),
                mapPayments(student.getPayments()),
                mapPresences(student.getPresences())
        );
    }

    private ClassroomSimpleDAO mapClassroom(Classroom classroom) {
        if (classroom == null) return null;
        return new ClassroomSimpleDAO(classroom.getId(), classroom.getClassType(), classroom.getName());
    }

    private List<FinancialResponsibleDAO> mapResponsibles(List<FinancialResponsible> list) {
        if (list == null) return List.of();
        return list.stream().map(fr ->
                new FinancialResponsibleDAO(fr.getId(), fr.getName(), fr.getPhone(), fr.getCpf())
        ).toList();
    }

    private List<PaymentSimpleDAO> mapPayments(List<Payment> list) {
        if (list == null) return List.of();
        return list.stream().map(p ->
                new PaymentSimpleDAO(p.getPaymentDate(), p.getAmount())
        ).toList();
    }

    private List<PresenceSimpleDAO> mapPresences(List<Presence> list) {
        if (list == null) return List.of();
        return list.stream().map(pres ->
                new PresenceSimpleDAO(pres.getDate(), pres.isPresence())
        ).toList();
    }
}
