package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.domain.Hotel;
import com.endava.endavel.batatasquad.exceptions.NotFoundException;
import com.endava.endavel.batatasquad.vo.HotelVO;

import java.util.List;

public interface IHotelService {
    List<Hotel> findHotelsByDestId(Destination destId) throws NotFoundException;
    List<Hotel> findHotelsByPricePerDay(Double pricePerDay) throws NotFoundException;
    List<Hotel> findHotelsByStars(Integer stars) throws NotFoundException;
    List<Hotel> findHotels();

    Hotel findHotelById(Integer id) throws NotFoundException;
    Hotel findHotelByName(String name) throws NotFoundException;
    Hotel findHotelByAddress(String address) throws NotFoundException;

    Hotel createHotel(HotelVO hotelVO);
    List<Hotel> createHotels(List<HotelVO> hotels);

    List<Hotel> filterHotels(Double pricePerDay, Integer destination, Integer stars);
}
