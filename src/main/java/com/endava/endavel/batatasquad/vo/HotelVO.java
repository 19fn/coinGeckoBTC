package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.Hotel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HotelVO {
    Integer id;
    String name;
    String description;
    Integer stars;
    Double pricePerDay;
    String address;
    Integer contId;
    Long destId;

    public HotelVO() {
    }
    
    public HotelVO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.stars = hotel.getStars();
        this.pricePerDay = hotel.getPricePerDay();
        this.address = hotel.getAddress();
        this.contId = null; // hotel.getContId().getId();
        this.destId = null; // hotel.getDestId().getId();
    }
}
