package com.month.spent.exceptions.user;

public class BadEmail extends RuntimeException {
    public BadEmail(String message) {
        super(message);
    }
}
