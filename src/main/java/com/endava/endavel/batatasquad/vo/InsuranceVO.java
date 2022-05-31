package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.Insurance;

public class InsuranceVO {
    
    public Long id;
    public Integer contactId;
    public String name;
    public String description;
    public Double pricePerDay;

    public InsuranceVO() {
    }

    public InsuranceVO(Integer contactId, String name, String description, Double pricePerDay) {
        this.contactId = contactId;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }

    public InsuranceVO(Insurance insurance) {
        this.contactId = null; // insurance.getContact().getId();
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.description = insurance.getDescription();
        this.pricePerDay = insurance.getPricePerDay();
    }
}
