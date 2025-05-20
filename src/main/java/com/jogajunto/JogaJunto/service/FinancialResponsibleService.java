package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.domain.financialresponsible.FinancialResponsible;
import com.jogajunto.JogaJunto.domain.financialresponsible.FinancialResponsibleRepository;
import com.jogajunto.JogaJunto.domain.financialresponsible.FinancialResponsibleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialResponsibleService {

    @Autowired
    FinancialResponsibleRepository repository;

    private List<FinancialResponsible> allFinancialResponsible(){
        var allFinancial = repository.findAll();
        return allFinancial;
    }

    private FinancialResponsible registerFinancialReponsible(FinancialResponsibleRequestDTO data) {
        if(exists(data)){
            System.out.println("responsavel financeiro já cadastrado");
        }
        FinancialResponsible financialResponsible = new FinancialResponsible(data);
        repository.save(financialResponsible);
        return financialResponsible;
    }

    protected boolean exists(FinancialResponsibleRequestDTO data) {
        var allFInancial = allFinancialResponsible();
        for (FinancialResponsible financialResponsible : allFInancial) {
            if (data.cpf().equals(financialResponsible.getCpf())){
                return true;
            }
        }
        return false;
    }

}
