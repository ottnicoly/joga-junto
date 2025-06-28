package com.jogajunto.JogaJunto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    //lista?
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classrooms;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Student(Integer id, String name, LocalDate birthDate, String motherName, String fatherName, String address, String phone, String cpf, List<FinancialResponsible> financialResponsibles, Classroom classrooms) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.address = address;
        this.phone = phone;
        this.cpf = cpf;
        this.financialResponsibles = financialResponsibles;
        this.classrooms = classrooms;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Classroom classrooms) {
        this.classrooms = classrooms;
    }

    public List<FinancialResponsible> getFinancialResponsibles() {
        return financialResponsibles;
    }

    public void setFinancialResponsibles(List<FinancialResponsible> financialResponsibles) {
        this.financialResponsibles = financialResponsibles;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}