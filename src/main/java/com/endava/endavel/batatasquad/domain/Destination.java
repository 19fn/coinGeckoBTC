package com.endava.endavel.batatasquad.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
@Getter
@Setter
public class Destination {
    // Primary Key
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="city_id")
    private City city;

    private Integer highSeasonStartMonth;
    
    private Integer highSeasonEndMonth;

    public Destination(City city, Integer highSeasonStartMonth, Integer highSeasonEndMonth) {
        this.city = city;
        this.highSeasonStartMonth = highSeasonStartMonth;
        this.highSeasonEndMonth = highSeasonEndMonth;
    }
}
