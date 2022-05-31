package com.endava.endavel.batatasquad.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class TransportCompany {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    List<TransportTicket> transportTickets;

    @OneToOne(cascade = CascadeType.ALL)
    Contact transportContact;

    public TransportCompany() {
    }

     public TransportCompany(Long id, String name, List<TransportTicket> transportTickets, Contact transportContact) {
        this.id = id;
        this.name = name;
        this.transportTickets = transportTickets;
        this.transportContact = transportContact;
    }

    public TransportCompany(String name, Contact transportContact) {
        this.name = name;
        this.transportContact = transportContact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TransportTicket> getTransportTickets() {
        return transportTickets;
    }

    public void setTransportTickets(List<TransportTicket> transportTickets) {
        this.transportTickets = transportTickets;
    }

    public Contact getTransportContact() {
        return transportContact;
    }

    public void setTransportContact(Contact transportContact) {
        this.transportContact = transportContact;
    }

    @Override
    public String toString() {
        return  name + '\'' +
                ", transportTickets=" + transportTickets +
                ", transportContact=" + transportContact +
                '}';
    }
}
