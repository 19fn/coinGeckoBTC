package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Contact;
import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.domain.Hotel;
import com.endava.endavel.batatasquad.exceptions.NotFoundException;
import com.endava.endavel.batatasquad.persistance.HotelRepository;
import com.endava.endavel.batatasquad.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements IHotelService{

    private HotelRepository hotelRepository;

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> findHotelsByDestId(Destination destId) {
        return hotelsValidation(hotelRepository.findHotelsByDestId(destId));
    }

    @Override
    public List<Hotel> findHotelsByPricePerDay(Double pricePerDay) {
        return hotelRepository.findHotelsByPricePerDay(pricePerDay);
    }

    @Override
    public List<Hotel> findHotelsByStars(Integer stars){
        return hotelRepository.findHotelsByStars(stars);
    }

    @Override
    public List<Hotel> findHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findHotelById(Integer id) {
        return hotelRepository.findHotelById(id);
    }

    @Override
    public Hotel findHotelByName(String name) {
        return hotelRepository.findHotelByName(name);
    }

    @Override
    public Hotel findHotelByAddress(String address) {
        return hotelRepository.findHotelByAddress(address);
    }

    @Override
    public Hotel createHotel(HotelVO hotelVO) {

        Contact contact = null; //TODO: find contact by id
        Destination destination = null; //TODO: find destination by id

        Hotel hotel = new Hotel(hotelVO, contact, destination);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> createHotels(List<HotelVO> hotels) {
        List<Hotel> response = hotels.stream().map((h) -> {
            Contact contact = null; //TODO: find contact by id
            Destination destination = null; //TODO: find destination by id

            return new Hotel(h, contact, destination);
        }).collect(Collectors.toList());
        
        return (List<Hotel>) hotelRepository.saveAll(response);
    }

    @Override
    public List<Hotel> filterHotels(Double pricePerDay,
                                    Integer destination,
                                    Integer stars) {

        List<Hotel> hotels = hotelsFiltered(pricePerDay, destination, stars);

        return hotelsValidation(hotels);
    }

    private List<Hotel> hotelsFiltered(Double pricePerDay,
                                       Integer destinationId,
                                       Integer stars) {

        List<Hotel> hotels = new ArrayList<>();

        Destination destination = null; //TODO: find destination by id

        // Case 1: there is a value for -> pricePerDay, destination and stars.
        if (pricePerDay != null && destination != null && stars != null){
            List<Hotel> hotelsByPricePerDay = findHotelsByPricePerDay(pricePerDay);
            List<Hotel> hotelsByDestination = findHotelsByDestId(destination);
            List<Hotel> hotelsByStars = findHotelsByStars(stars);

            return extractSameHotels(hotels, hotelsByPricePerDay, hotelsByDestination, hotelsByStars);

            // Case 2: there is a value for -> pricePerDay and destination.
        }else if (pricePerDay != null && destination != null) {
            List<Hotel> hotelsByPricePerDay = findHotelsByPricePerDay(pricePerDay);
            List<Hotel> hotelsByDestination = findHotelsByDestId(destination);

            return extractSameHotels(hotels, hotelsByPricePerDay, hotelsByDestination);

            // Case 3: there is a value for -> pricePerDay and stars.
        }else if (pricePerDay != null && stars != null) {
            List<Hotel> hotelsByPricePerDay = findHotelsByPricePerDay(pricePerDay);
            List<Hotel> hotelsByStars = findHotelsByStars(stars);

            return extractSameHotels(hotels, hotelsByPricePerDay, hotelsByStars);

            // Case 4: there is a value for -> stars and destination.
        }else if (destination != null && stars != null) {
            List<Hotel> hotelsByDestination = findHotelsByDestId(destination);
            List<Hotel> hotelsByStars = findHotelsByStars(stars);

            return extractSameHotels(hotels, hotelsByStars, hotelsByDestination);

            // Case 5: there is a value ONLY for -> destination.
        }else if (destination != null) {
            return findHotelsByDestId(destination);

            // Case 6: there is a value ONLY for -> stars.
        }else if (stars != null) {
            return findHotelsByStars(stars);

            // Case 7: there is a value ONLY for -> pricePerDay.
        }else if (pricePerDay != null) {
            return findHotelsByPricePerDay(pricePerDay);

            // Case 8: there is NO a values.
        }else{
            return findHotels();
        }

    }

    // Extract Hotels from 2 hotels.
    private List<Hotel> extractSameHotels(List<Hotel> hotels,
                                          List<Hotel> hotels1,
                                          List<Hotel> hotels2) {
        for(Hotel hotel : hotels1) {
            if (hotels2.contains(hotel)){
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    // Extract Hotels from 3 hotels.
    private List<Hotel> extractSameHotels(List<Hotel> hotels,
                                          List<Hotel> hotels1,
                                          List<Hotel> hotels2,
                                          List<Hotel> hotels3) {

        for(Hotel hotel : hotels1) {
            if (hotels2.contains(hotel) && hotels3.contains(hotel)){
                hotels.add(hotel);
            }
        }
        return hotels;

    }

    private List<Hotel> hotelsValidation(List<Hotel> hotels) {
        if ( hotels.isEmpty() ){
            throw new NotFoundException("Hotels not found");
        }else{
            return hotels;
        }
    }

}
