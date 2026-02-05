package com.driagon.accounts.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -789838875879088226L;

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}