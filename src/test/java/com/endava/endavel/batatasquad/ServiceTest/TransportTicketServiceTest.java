package com.endava.endavel.batatasquad.ServiceTest;

import com.endava.endavel.batatasquad.domain.City;
import com.endava.endavel.batatasquad.domain.Contact;
import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.domain.TransportCompany;
import com.endava.endavel.batatasquad.persistance.TransportCompanyRepository;
import com.endava.endavel.batatasquad.services.ContactService;
import com.endava.endavel.batatasquad.services.TransportCompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TransportTicketServiceTest {
    @Autowired
    private TransportCompanyService service;

    @MockBean
    private TransportCompanyRepository transportCompanyRepository;

    @MockBean
    private ContactService conctactService;

    @Test
    public void makeReservation(){
        TransportCompany transportCompany = new TransportCompany();
        Contact contact = new Contact();
        City city = new City();
        Destination destination = new Destination();

    }
}
