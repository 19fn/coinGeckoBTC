package com.endava.endavel.batatasquad.persistance;

import com.endava.endavel.batatasquad.domain.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CitiesRepository extends CrudRepository<City, Integer> {
    Optional<City> getCityByNameAndProvince(String name, String province);
}