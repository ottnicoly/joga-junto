package com.jogajunto.JogaJunto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String address;
    private String phone;
    private String cpf;

    @ManyToMany
    @JoinTable(
            name = "student_financial_responsible",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "financial_responsible_id")
    )
    private List<FinancialResponsible> financialResponsibles;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classrooms;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private Double monthlyFee;

    private boolean paymentUpToDate = false;

    @ManyToMany(mappedBy = "students")
    private List<Payment> payments;

    @ManyToMany(mappedBy = "student")
    private List<Presence> presences;

}