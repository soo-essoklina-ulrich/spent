package com.month.spent.exceptions.user;

public class EmailExiste extends RuntimeException {
    public EmailExiste(String message) {
        super(message);
    }
}
