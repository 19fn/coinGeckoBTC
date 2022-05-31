package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.City;
import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.exceptions.DuplicatesException;
import com.endava.endavel.batatasquad.exceptions.NotFoundException;
import com.endava.endavel.batatasquad.exceptions.ValidationException;
import com.endava.endavel.batatasquad.persistance.DestinationsRepository;
import com.endava.endavel.batatasquad.vo.DestinationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationsService implements IDestinationsService {
    private DestinationsRepository destinationsRepository;
    private CitiesService citiesService;

    @Autowired
    public void setDestinationsRepository(DestinationsRepository destinationsRepository) {
        this.destinationsRepository = destinationsRepository;
    }

    @Autowired
    public void setCitiesService(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @Override
    public List<Destination> getAllDestinations() {
        return (List<Destination>) destinationsRepository.findAll();
    }

    @Override
    public Destination getDestinationById(Integer id) {
        return destinationsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The destination with id " + id + " doesn't exist"));
    }

    @Override
    public Destination getDestinationByCityId(Integer cityId) {
        return destinationsRepository.getDestinationByCityId(cityId)
                .orElseThrow(() -> new NotFoundException("The destination with city id " + cityId + " doesn't exist"));
    }

    @Override
    public Destination saveDestination(DestinationVO destinationVO) {
        validateDestinationVOValues(destinationVO);
        City city = citiesService.findIfExistsOrSaveCity(destinationVO.cityName, destinationVO.cityProvince);
        validateCityIsNotAssociatedToAPersistedDestination(city);
        return destinationsRepository.save(
                new Destination(city, destinationVO.highSeasonStartMonth, destinationVO.highSeasonEndMonth));
    }

    @Override
    public List<Destination> saveAllDestinations(List<DestinationVO> destinationsVOS) {
        List<Destination> destinations = new ArrayList<>();
        for (DestinationVO destinationVO : destinationsVOS) {
            validateDestinationVOValues(destinationVO);
            City city = citiesService.findIfExistsOrSaveCity(destinationVO.cityName, destinationVO.cityProvince);
            validateCityIsNotAssociatedToADestinationInList(city, destinations);
            validateCityIsNotAssociatedToAPersistedDestination(city);

            destinations.add(new Destination(city, destinationVO.highSeasonStartMonth, destinationVO.highSeasonEndMonth));
        }
        return (List<Destination>) destinationsRepository.saveAll(destinations);
    }

    private void validateDestinationVOValues(DestinationVO destinationVO) {
        validateMonthsValuesInDestinationVO(destinationVO);
        citiesService.validateNameAndProvinceAreOk(destinationVO.cityName, destinationVO.cityProvince);
    }

    private void validateMonthsValuesInDestinationVO(DestinationVO destinationVO) {
        if(destinationVO.highSeasonStartMonth == null || destinationVO.highSeasonEndMonth == null ) {
            throw new ValidationException("The start and end months of the high season must be fixed");
        }
        if(destinationVO.highSeasonStartMonth < 1 || destinationVO.highSeasonStartMonth > 12) {
            throw new ValidationException("The high season start month must be a number between 1 and 12");
        }
        if(destinationVO.highSeasonEndMonth < 1 || destinationVO.highSeasonEndMonth > 12) {
            throw new ValidationException("The high season end month must be a number between 1 and 12");
        }
    }

    private void validateCityIsNotAssociatedToADestinationInList(City city, List<Destination> destinations) {
        for(Destination destination : destinations) {
            if(destination.getCity().getId().equals(city.getId())) {
                throw new DuplicatesException("The city with name " + city.getName() + " and province "
                        + city.getProvince() + " is repeated in the destinations list received");
            }
        }
    }

    private void validateCityIsNotAssociatedToAPersistedDestination(City city) {
        if(destinationsRepository.getDestinationByCityId(city.getId()).isPresent()){
            throw new DuplicatesException("There is already a destination with city " + city.getName() + " in the" +
                    " province " + city.getProvince());
        }
    }
}
