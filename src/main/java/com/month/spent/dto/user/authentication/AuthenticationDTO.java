package com.month.spent.dto.user.authentication;

import org.springframework.http.converter.HttpMessageNotReadableException;

public record AuthenticationDTO(
        String username,
        String password
) {
    public AuthenticationDTO {
        if (username == null || username.isBlank()) {
            throw new HttpMessageNotReadableException("Username cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new HttpMessageNotReadableException("Password cannot be null or empty");
        }
    }

}
