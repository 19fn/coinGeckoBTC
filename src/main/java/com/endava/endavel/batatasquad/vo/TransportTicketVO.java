package com.endava.endavel.batatasquad.vo;
import com.endava.endavel.batatasquad.domain.TransportTicket;

public class TransportTicketVO {
    public Long id;

    public String description;

    public Double price;

    public Long fromCity;

    public Long toDestination;

    public Long transportCompany;

    public TransportTicketVO(Long id, String description, Double price, Long fromCity, Long toDestination, Long transportCompany) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.fromCity = fromCity;
        this.toDestination = toDestination;
        this.transportCompany = transportCompany;
    }

    public TransportTicketVO(TransportTicket transportTicket) {
        this.id = transportTicket.getId();
        this.description = transportTicket.getDescription();
        this.price = transportTicket.getPrice();
        this.fromCity = null; // transportTicket.getFromCity().getId();
        this.toDestination = null; // transportTicket.getToDestination().getId();
        this.transportCompany = transportTicket.getTransportCompany().getId();
    }
}
