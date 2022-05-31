package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.Destination;

public class DestinationValueObject {
    public Integer id;
    public Integer cityId;
    public Integer highSeasonStartMonth;
    public Integer highSeasonEndMonth;

    public DestinationValueObject(){}

    public DestinationValueObject(Destination destination) {
        this.id = destination.getId();
        this.cityId = destination.getCity().getId();
        this.highSeasonStartMonth = destination.getHighSeasonStartMonth();
        this.highSeasonEndMonth = destination.getHighSeasonEndMonth();
    }
}