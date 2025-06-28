package com.jogajunto.JogaJunto.dao.student;

import java.time.LocalDate;

public record PaymentSimpleDAO(
        LocalDate paymentDate,
        Double amount
) {
}
