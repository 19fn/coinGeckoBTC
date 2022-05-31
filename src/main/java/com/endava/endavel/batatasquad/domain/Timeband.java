package com.endava.endavel.batatasquad.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Getter
@Setter
public class Timeband {
    @Id @GeneratedValue
    private Integer id;
// TODO: cambie name por descripción
    private String name;
}
