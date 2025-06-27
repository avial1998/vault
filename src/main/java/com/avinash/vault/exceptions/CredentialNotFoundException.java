package com.avinash.vault.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CredentialNotFoundException extends RuntimeException{
    public CredentialNotFoundException(String message) {
        super(message);
    }

}
