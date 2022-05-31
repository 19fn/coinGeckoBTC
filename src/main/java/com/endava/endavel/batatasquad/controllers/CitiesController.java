package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.domain.City;
import com.endava.endavel.batatasquad.exceptions.NotFoundException;
import com.endava.endavel.batatasquad.exceptions.ValidationException;
import com.endava.endavel.batatasquad.services.ICitiesService;
import com.endava.endavel.batatasquad.vo.CityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/batatasquad/city")
public class CitiesController {
    private final ICitiesService citiesService;

    @Autowired
    public CitiesController(ICitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping
    public ResponseEntity<List<CityVO>> getAllCitiesVOS(){
        return ResponseEntity.ok(convertCitiesToVOS(citiesService.getAllCities()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityVO> getCityVOById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(convertCityToVO(citiesService.getCityById(id)));
    }

    @PostMapping
    public ResponseEntity<CityVO> saveCity(@RequestBody CityVO cityVO) throws ValidationException {
        return new ResponseEntity<>(convertCityToVO(citiesService.saveCity(cityVO)), HttpStatus.CREATED);
    }

    @PostMapping("/many")
    public ResponseEntity<List<CityVO>> saveAllCities(@RequestBody List<CityVO> citiesVOS)
            throws ValidationException {
        return new ResponseEntity<>(convertCitiesToVOS(citiesService.saveAllCities(citiesVOS)), HttpStatus.CREATED);
    }

    private List<CityVO> convertCitiesToVOS(List<City> cities) {
        return cities.stream()
                .map(this::convertCityToVO)
                .collect(Collectors.toList());
    }

    private CityVO convertCityToVO(City city) {
        return new CityVO(city);
    }
}