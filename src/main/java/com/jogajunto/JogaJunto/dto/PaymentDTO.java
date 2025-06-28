package com.jogajunto.JogaJunto.dto;

import java.time.LocalDate;

public record PaymentDTO(
        LocalDate paymentDate,
        String payer,
        Double amount
) {
}
