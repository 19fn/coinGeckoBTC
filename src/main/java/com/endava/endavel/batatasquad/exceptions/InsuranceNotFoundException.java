package com.endava.endavel.batatasquad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsuranceNotFoundException extends RuntimeException {
    public InsuranceNotFoundException(String message) {
        super(message);
    }
}
