package com.endava.endavel.batatasquad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Package {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Destination destination;

    @ManyToOne
    private TransportTicket transportTicket;

    @ManyToOne
    private Insurance insurance;

    private Boolean highSeason;

    private Long days;

    public Package() {
    }

    public Package(Long id, Destination destination, TransportTicket transportTicket, Insurance insurance,
            Boolean highSeason, Long days) {
        this.id = id;
        this.destination = destination;
        this.transportTicket = transportTicket;
        this.insurance = insurance;
        this.highSeason = highSeason;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public TransportTicket getTransportTicket() {
        return transportTicket;
    }

    public void setTransportTicket(TransportTicket transportTicket) {
        this.transportTicket = transportTicket;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Boolean isHighSeason() {
        return highSeason;
    }

    public void setHighSeason(Boolean highSeason) {
        this.highSeason = highSeason;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    
}
