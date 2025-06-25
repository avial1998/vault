package com.avinash.vault.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidRequestDetailsException extends RuntimeException{
    public InvalidRequestDetailsException(String message) {
        super(message);
    }
}
