package com.month.spent.dto.exception;

public record ExceptionDto(
        org.springframework.http.HttpStatus code,
        String message
) {
}
