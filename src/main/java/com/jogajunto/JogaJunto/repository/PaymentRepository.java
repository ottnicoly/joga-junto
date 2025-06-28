package com.jogajunto.JogaJunto.repository;

import com.jogajunto.JogaJunto.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}

