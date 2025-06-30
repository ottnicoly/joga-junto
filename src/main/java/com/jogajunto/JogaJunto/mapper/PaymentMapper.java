package com.jogajunto.JogaJunto.mapper;

import com.jogajunto.JogaJunto.dao.PaymentDAO;
import com.jogajunto.JogaJunto.model.Payment;

public class PaymentMapper {
    public static PaymentDAO toDAO(Payment p) {
        return new PaymentDAO(
                p.getId(),
                p.getResponsible().getName(),
                p.getAmount(),
                p.getPaymentDate().toString()
        );
    }
}
