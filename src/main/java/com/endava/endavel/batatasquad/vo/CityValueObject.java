package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.City;

public class CityValueObject {
    public Integer id;
    public String name;
    public String province;

    public CityValueObject() {}

    public CityValueObject(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.province = city.getProvince();
    }
}