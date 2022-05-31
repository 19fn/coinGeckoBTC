package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.Destination;

public class DestinationVO {
    public Integer id;
    public String cityName;
    public String cityProvince;
    public Integer highSeasonStartMonth;
    public Integer highSeasonEndMonth;

    public DestinationVO(){}

    public DestinationVO(Destination destination) {
        this.id = destination.getId();
        this.cityName = destination.getCity().getName();
        this.cityProvince = destination.getCity().getProvince();
        this.highSeasonStartMonth = destination.getHighSeasonStartMonth();
        this.highSeasonEndMonth = destination.getHighSeasonEndMonth();
    }
}