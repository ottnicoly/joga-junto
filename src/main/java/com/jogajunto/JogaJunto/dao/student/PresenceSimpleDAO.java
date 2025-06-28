package com.jogajunto.JogaJunto.dao.student;

import java.time.LocalDate;

public record PresenceSimpleDAO(
        LocalDate date,
        Boolean present
) {
}
