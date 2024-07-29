package com.ednaldo.ecommerce.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException() {
        super("Password inválida.");
    }
}
