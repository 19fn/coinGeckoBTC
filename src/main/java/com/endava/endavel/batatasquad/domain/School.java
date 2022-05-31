package com.endava.endavel.batatasquad.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private Boolean isPublic;

    public School(String name, City city, Boolean isPublic) {
        this.name = name;
        this.city = city;
        this.isPublic = isPublic;
    }
}