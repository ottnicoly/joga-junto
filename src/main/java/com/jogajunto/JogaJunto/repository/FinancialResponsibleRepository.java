package com.jogajunto.JogaJunto.repository;

import com.jogajunto.JogaJunto.model.FinancialResponsible;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FinancialResponsibleRepository extends JpaRepository<FinancialResponsible, Integer> {

    Optional<FinancialResponsible> findByCpf(String cpf);

    FinancialResponsible findByNameContainingIgnoreCase(String name);

}
