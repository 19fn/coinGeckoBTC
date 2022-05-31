package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.exceptions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({
        TransportCompanyNotFound.class,
        TransportTicketNotFound.class,
        NotFoundException.class,
        InsuranceNotFoundException.class,
    })
    public ResponseEntity<Object> handleNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({
        ValidationException.class,
        MethodArgumentTypeMismatchException.class,
    })
    public ResponseEntity<Object> handleBadRequestException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler({
            DuplicatesException.class,
    })
    public ResponseEntity<Object> handleDuplicatesException(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
