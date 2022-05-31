package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Contact;

public interface IContactService {
    Contact saveContact(String name, String phone, String email);
}
