package com.jogajunto.JogaJunto.domain.student;

import com.jogajunto.JogaJunto.domain.financialresponsible.FinancialResponsible;
import com.jogajunto.JogaJunto.domain.financialresponsible.FinancialResponsibleRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String school;
    private String grade;
    private Period period;
    private String address;
    private String phone;
    private String cpf;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "student_financial_responsible",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "financial_responsible_id")
    )
    private List<FinancialResponsible> financialResponsibles;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private List<Classroom> classrooms;

    // TO-DO colocar tudo isso em um service:
    // TO-DO validar se o estudante ja existe (validar as iniciais dos nomes, validar a data de nascimento, validar o cpf)
    // TO-DO validar se o responsavel financeiro ja existe
    public Student(StudentRequestDTO data){
        this.name = data.name();
        this.birthDate = data.birthDate();
        this.motherName = data.motherName();
        this.fatherName = data.fatherName();
        this.school = data.school();
        this.grade = data.grade();
        this.period = data.period();
        this.address = data.address();
        this.phone = data.phone();
        this.cpf = data.cpf();
        this.financialResponsibles = new ArrayList<>();
        for (FinancialResponsibleRequestDTO responsibleDTO : data.financialResponsibles()){
            FinancialResponsible financialResponsible = new FinancialResponsible(responsibleDTO);
            this.financialResponsibles.add(financialResponsible);
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getSchool() {
        return school;
    }

    public String getGrade() {
        return grade;
    }

    public Period getPeriod() {
        return period;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpf() {
        return cpf;
    }

    public List<FinancialResponsible> getFinancialResponsibles() {
        return financialResponsibles;
    }
}