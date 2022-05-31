package com.endava.endavel.batatasquad.persistance;

import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.domain.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {
    List<Hotel> findAll();
    List<Hotel> findHotelsByStars(Integer stars);
    List<Hotel> findHotelsByDestId(Destination destId);
    List<Hotel> findHotelsByPricePerDay(Double pricePerDay);

    Hotel findHotelById(Integer id);
    Hotel findHotelByName(String name);
    Hotel findHotelByAddress(String address);
}
