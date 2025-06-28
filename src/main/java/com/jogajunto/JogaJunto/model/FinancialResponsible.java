package com.jogajunto.JogaJunto.model;

import com.jogajunto.JogaJunto.dto.FinancialResponsibleRequestDTO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "financial_responsible")
public class FinancialResponsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phone;
    private String cpf;

    @ManyToMany(mappedBy = "financialResponsibles")
    private List<Student> students;

    public FinancialResponsible (FinancialResponsibleRequestDTO data) {
        this.name = data.name();
        this.phone = data.phone();
        this.cpf = data.cpf();
    }

    public String getCpf() {
        return cpf;
    }
}
