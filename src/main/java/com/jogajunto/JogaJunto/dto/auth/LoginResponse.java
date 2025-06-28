package com.jogajunto.JogaJunto.dto.auth;

public record LoginResponse(
        String acessToken,
        Long expiresIn
) {
}
