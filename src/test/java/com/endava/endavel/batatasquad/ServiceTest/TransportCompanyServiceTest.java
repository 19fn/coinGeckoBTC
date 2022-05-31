package com.endava.endavel.batatasquad.ServiceTest;

import com.endava.endavel.batatasquad.domain.Contact;
import com.endava.endavel.batatasquad.domain.TransportCompany;
import com.endava.endavel.batatasquad.exceptions.TransportCompanyNotFound;
import com.endava.endavel.batatasquad.persistance.TransportCompanyRepository;
import com.endava.endavel.batatasquad.services.ContactService;
import com.endava.endavel.batatasquad.services.TransportCompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransportCompanyServiceTest {

    @Autowired
    private TransportCompanyService service;

    @MockBean
    private TransportCompanyRepository transportCompanyRepository;

    @MockBean
    private ContactService conctactService;

    @Test
    public void getCompanies(){
        List<TransportCompany> companiesInSystem = new ArrayList<>();
        TransportCompany newTransportCompany = new TransportCompany();
        Contact newContact = new Contact();
        newContact.setEmail("flechabus@Gmail.com");
        newContact.setPhone("0810-222-4533");
        newTransportCompany.setName("FlechaBus");
        newTransportCompany.setTransportContact(newContact);
        companiesInSystem.add(newTransportCompany);
        when(transportCompanyRepository.findAll()).thenReturn(companiesInSystem);

        List<TransportCompany> result = service.getAllTransportCompanies();

        assertEquals(1,result.size());
        assertEquals("FlechaBus", result.get(0).getName());
        verify(transportCompanyRepository).findAll();
    }

    @Test
    public void getCompanyByName(){
        TransportCompany newTransportCompany = new TransportCompany();
        Contact newContact = new Contact();
        newContact.setEmail("flechabus@Gmail.com");
        newContact.setPhone("0810-222-4533");
        newTransportCompany.setName("FlechaBus");
        newTransportCompany.setTransportContact(newContact);
        when(transportCompanyRepository.findByName("FlechaBus")).thenReturn(newTransportCompany);

        TransportCompany result = transportCompanyRepository.findByName("FlechaBus");
        assertEquals("FlechaBus",result.getName());
        assertEquals("flechabus@Gmail.com",result.getTransportContact().getEmail());
        assertNotNull(result);
    }

    @Test
    public void getCompanyByNameNotFound(){
        TransportCompany transportCompany = new TransportCompany();
        Contact contact = new Contact();
        contact.setEmail("flechabus@Gmail.com");
        contact.setPhone("0810-222-4533");
        transportCompany.setName("FlechaBus");
        transportCompany.setTransportContact(contact);
        String name = transportCompany.getName();
        when(transportCompanyRepository.findByName(name)).thenThrow(TransportCompanyNotFound.class);

        assertThrows(TransportCompanyNotFound.class, ()->{
            service.getCompanyByName(name);
        });
    }

    @Test
    public void addTransportCompany(){
        Contact contact = new Contact();
        contact.setEmail("flechabus@Gmail.com");
        contact.setPhone("0810-222-4533");
        TransportCompany transportCompany = new TransportCompany("Flechabus",contact);
        when(conctactService.saveContact(contact.getName(),contact.getPhone(),contact.getEmail())).thenReturn(contact);
        when(transportCompanyRepository.save(transportCompany)).thenReturn(transportCompany);
        when(transportCompanyRepository.findByName("Flechabus")).thenReturn(transportCompany);

        assertEquals("Flechabus",service.getCompanyByName("Flechabus").getName());
        assertEquals("flechabus@Gmail.com",service.getCompanyByName("Flechabus").getTransportContact().getEmail());
    }

    @Test
    public void deleteAnTransportNotFound(){
        Contact contact = new Contact();
        contact.setEmail("flechabus@Gmail.com");
        contact.setPhone("0810-222-4533");
        TransportCompany transportCompany = new TransportCompany("Flechabus",contact);
        doThrow(TransportCompanyNotFound.class).when(transportCompanyRepository).deleteById(transportCompany.getId());
        assertThrows(TransportCompanyNotFound.class, ()->{
           service.deleteById(transportCompany.getId());
        });

    }

    @Test
    public void deleteAnTransportCompany(){
        Contact contact = new Contact();
        contact.setEmail("flechabus@Gmail.com");
        contact.setPhone("0810-222-4533");
        TransportCompany transportCompany = new TransportCompany("Flechabus",contact);
        when(transportCompanyRepository.findById(transportCompany.getId())).thenReturn(Optional.of(transportCompany));
        //verifica que se llame el mock
        service.deleteById(transportCompany.getId());
        verify(transportCompanyRepository).delete(transportCompany);
        verify(transportCompanyRepository).findById(transportCompany.getId());

        //verify(transportCompanyRepository,never()).delete(transportCompany); si corre este mock, algo anda mal
    }



}
