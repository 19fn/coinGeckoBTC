package com.endava.endavel.batatasquad.exceptions;

public class TransportCompanyNotFound extends RuntimeException{

    public TransportCompanyNotFound(Long id) {
        super("Transport company with id " + id + " not found");
    }

    public TransportCompanyNotFound() {
    }

    public TransportCompanyNotFound(String string) {
    }

}
