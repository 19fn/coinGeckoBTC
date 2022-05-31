package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.TransportTicket;

import java.util.List;

public interface ITransportTicketService {
    public TransportTicket makeReservation(Long transportCompanyId, String description, double price, Long fromCityId, Long toDestinationId);
    public void deleteReservation(Long id);
    List<TransportTicket> getAllTickets();
}
