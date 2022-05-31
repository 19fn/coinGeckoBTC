package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.TransportCompany;

public class TransportCompanyVO {
    public Long id;

    public String name;

    public ContactVO transportContact;

    public TransportCompanyVO(Long id, String name, ContactVO transportContact) {
        this.id = id;
        this.name = name;
        this.transportContact = transportContact;
    }

    public TransportCompanyVO(TransportCompany transportCompany) {
        this.id = transportCompany.getId();
        this.name = transportCompany.getName();
        this.transportContact = new ContactVO(transportCompany.getTransportContact());
    }
}
