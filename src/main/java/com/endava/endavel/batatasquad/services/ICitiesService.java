package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.City;
import com.endava.endavel.batatasquad.vo.CityVO;

import java.util.List;

public interface ICitiesService {
    List<City> getAllCities();
    City getCityById(Integer id);
    City getCityByNameAndProvince(String name, String province);

    City saveCity(CityVO cityVO);
    City saveCity(String name, String province);
    List<City> saveAllCities(List<CityVO> citiesVOS);

    City findIfExistsOrSaveCity(String name, String province);
}
