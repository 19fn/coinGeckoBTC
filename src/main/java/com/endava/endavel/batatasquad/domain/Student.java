package com.endava.endavel.batatasquad.domain;

import lombok.*;
import javax.persistence.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "contact_tutor_id")
    private Contact contactTutor;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private String name;

    private Integer travelYear;

    private LocalDate dateOfBirth;

    public Student(School school, Contact contactTutor, Contact contact, String name, Integer travelYear, LocalDate dateOfBirth) {
        this.school = school;
        this.contactTutor = contactTutor;
        this.contact = contact;
        this.name = name;
        this.travelYear = travelYear;
        this.dateOfBirth = dateOfBirth;
    }
}
