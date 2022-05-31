package com.endava.endavel.batatasquad.persistance;

import com.endava.endavel.batatasquad.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Contact deleteByNameAndPhone(String name,String phone);
    Contact findByNameAndPhone(String name,String phone);
}
