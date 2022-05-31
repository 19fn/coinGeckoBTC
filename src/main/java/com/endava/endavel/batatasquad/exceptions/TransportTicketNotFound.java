package com.endava.endavel.batatasquad.exceptions;

public class TransportTicketNotFound extends RuntimeException{

    public TransportTicketNotFound(Long id) {
        super("Transport ticket with id " + id + " not found");
    }
}
