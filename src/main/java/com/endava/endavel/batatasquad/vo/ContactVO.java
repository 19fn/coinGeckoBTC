package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.Contact;

public class ContactVO {
    public Integer id;

    public String name;

    public String phone;

    public String email;

    public ContactVO(Integer id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public ContactVO(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
    }
}
