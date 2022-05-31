package com.endava.endavel.batatasquad.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@ToString
@Data
@Getter
@Setter
public class City {
    // Primary Key
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String province;

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }
}