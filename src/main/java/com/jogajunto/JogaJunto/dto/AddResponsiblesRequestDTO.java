package com.jogajunto.JogaJunto.dto;

import java.util.List;

public record AddResponsiblesRequestDTO(
        Integer student_id,
        List<FinancialResponsibleRequestDTO> responsibles
) {
}
