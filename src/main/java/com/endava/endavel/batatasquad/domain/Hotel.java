package com.endava.endavel.batatasquad.domain;

import lombok.*;
import javax.persistence.*;

import com.endava.endavel.batatasquad.vo.HotelVO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    private String address;
    private String name;
    private String description;
    private Double pricePerDay;
    private Integer stars;

    @ManyToOne
    private Contact contId;

    @ManyToOne
    private Destination destId;

    public Hotel(String name,
                 String description,
                 String address,
                 Destination destId,
                 Contact contId,
                 Double pricePerDay,
                 Integer stars) {

        this.name = name;
        this.description = description;
        this.address = address;
        this.destId = destId;
        this.contId = contId;
        this.pricePerDay = pricePerDay;
        this.stars = stars;
    }

    public Hotel(HotelVO hotelVO, Contact cont, Destination dest) {
        this.name = hotelVO.getName();
        this.description = hotelVO.getDescription();
        this.address = hotelVO.getAddress();
        this.destId = dest;
        this.contId = cont;
        this.pricePerDay = hotelVO.getPricePerDay();
        this.stars = hotelVO.getStars();
    }
}
