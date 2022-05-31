package com.endava.endavel.batatasquad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransportTicket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private TransportCompany transportCompany;

    private String description;

    private Double price;


    @ManyToOne
    private City fromCity;

    @ManyToOne
    private Destination toDestination;

    public TransportTicket() {
    }


    public TransportTicket(Long id, TransportCompany transportCompany, String description, Double price, City fromCity, Destination toDestination) {

        this.id = id;
        this.transportCompany = transportCompany;
        this.description = description;
        this.price = price;
        this.fromCity = fromCity;
        this.toDestination = toDestination;
    }

    public TransportTicket(TransportCompany transportCompany, String description, Double price, City fromCity, Destination toDestination) {
        this.transportCompany = transportCompany;
        this.description = description;
        this.price = price;
        this.fromCity = fromCity;
        this.toDestination = toDestination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public Destination getToDestination() {
        return toDestination;
    }

    public void setToDestination(Destination toDestination) {
        this.toDestination = toDestination;
    }

    @Override
    public String toString() {
        return "TransportTicket{" +
                "id=" + id +
                ", transportCompany=" + transportCompany +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", fromCity=" + fromCity +
                ", toDestination=" + toDestination +
                '}';
    }
    

}
