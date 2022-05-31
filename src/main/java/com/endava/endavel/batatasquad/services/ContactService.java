package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Contact;
import com.endava.endavel.batatasquad.persistance.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements  IContactService{

    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact saveContact(String name, String phone, String email){
        Contact contact = new Contact(name,phone,email);
        return contactRepository.save(contact);
    }

}
