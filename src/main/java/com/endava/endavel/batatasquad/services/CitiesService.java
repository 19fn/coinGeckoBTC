package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.City;
import com.endava.endavel.batatasquad.exceptions.DuplicatesException;
import com.endava.endavel.batatasquad.exceptions.NotFoundException;
import com.endava.endavel.batatasquad.exceptions.ValidationException;
import com.endava.endavel.batatasquad.persistance.CitiesRepository;
import com.endava.endavel.batatasquad.vo.CityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesService implements ICitiesService {
    private CitiesRepository citiesRepository;

    @Autowired
    public void setCitiesRepository(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Override
    public List<City> getAllCities() {
        return (List<City>) citiesRepository.findAll();
    }

    @Override
    public City getCityById(Integer id) {
        return citiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The city with id " + id + " doesn't exist"));
    }

    @Override
    public City getCityByNameAndProvince(String name, String province) {
        String upperCaseName = name.toUpperCase();
        String upperCaseProvince = province.toUpperCase();
        return citiesRepository.getCityByNameAndProvince(upperCaseName, upperCaseProvince)
                .orElseThrow(() -> new NotFoundException("The city with name " + upperCaseName +
                        " and province " + upperCaseProvince + " doesn't exist"));
    }

    @Override
    public List<City> saveAllCities(List<CityVO> citiesVOS) {
        List<City> cities = new ArrayList<>();
        for (CityVO cityVO : citiesVOS) {
            validateNameAndProvinceAreOk(cityVO.name, cityVO.province);
            validateNameAndProvinceArentInACityInList(cityVO.name, cityVO.province, cities);
            validateNameAndProvinceArentPersistedInACity(cityVO.name, cityVO.province);

            City city = convertNameAndProvinceToCity(cityVO.name, cityVO.province);
            cities.add(city);
        }
        return (List<City>) citiesRepository.saveAll(cities);
    }

    @Override
    public City saveCity(CityVO cityVO) {
        return saveCity(cityVO.name, cityVO.province);
    }

    @Override
    public City saveCity(String name, String province) {
        validateNameAndProvinceAreOk(name, province);
        validateNameAndProvinceArentPersistedInACity(name, province);
        return citiesRepository.save(convertNameAndProvinceToCity(name, province));
    }

    @Override
    public City findIfExistsOrSaveCity(String name, String province) {
        City city;
        try {
            city = getCityByNameAndProvince(name, province);
        } catch (NotFoundException e) {
            city = citiesRepository.save(convertNameAndProvinceToCity(name, province));
        }
        return city;
    }

    public void validateNameAndProvinceAreOk(String name, String province) {
        if(name == null || name.length() < 3) {
            throw new ValidationException("The/A city name was not indicated or is erroneous");
        }
        if(province == null || province.length() < 5) {
            throw new ValidationException("The/A city province was not indicated or is erroneous");
        }
    }

    private void validateNameAndProvinceArentInACityInList(String name, String province, List<City> cities) {
        for (City city : cities) {
            if(city.getName().equalsIgnoreCase(name) &&  city.getProvince().equalsIgnoreCase(province)) {
                throw new DuplicatesException("The city with name " + name
                        + " and province " + province + " is repeated in the received list");
            }
        }
    }

    private void validateNameAndProvinceArentPersistedInACity(String name, String province) {
        try {
            getCityByNameAndProvince(name, province);
            throw new DuplicatesException("There is already persisted a city with name " + name
                    + " and province " + province);
        } catch (NotFoundException ignored) {}
    }

    private City convertNameAndProvinceToCity(String name, String province) {
        String upperCaseName = name.toUpperCase();
        String upperCaseProvince = province.toUpperCase();
        return new City(upperCaseName, upperCaseProvince);
    }
}