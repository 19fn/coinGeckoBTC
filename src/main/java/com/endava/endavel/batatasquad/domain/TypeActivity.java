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
public class TypeActivity {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
