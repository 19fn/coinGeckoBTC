package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.City;

public class CityVO {
    public Integer id;
    public String name;
    public String province;

    public CityVO() {}

    public CityVO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.province = city.getProvince();
    }
}