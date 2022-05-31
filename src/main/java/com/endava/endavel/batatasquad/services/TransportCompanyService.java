package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Contact;
import com.endava.endavel.batatasquad.domain.TransportCompany;
import com.endava.endavel.batatasquad.exceptions.DuplicatesException;
import com.endava.endavel.batatasquad.exceptions.TransportCompanyNotFound;
import com.endava.endavel.batatasquad.exceptions.ValidationException;
import com.endava.endavel.batatasquad.persistance.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportCompanyService implements ITransportCompanyService{

    private TransportCompanyRepository transportCompanyRepository;
    private ContactService conctactService;

    @Autowired
    public void setTransportCompanyRepository(TransportCompanyRepository transportCompanyRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
    }

    @Autowired
    public void setConctactService(ContactService conctactService) {
        this.conctactService = conctactService;
    }

    @Override
    public TransportCompany addTransportCompany( String name, String phone, String email) {
        TransportCompany isThisCompanyInRepository = transportCompanyRepository.findByName(name);
        TransportCompany transportCompany = null;
        nameOrPhoneOrEmailInvalid(name,phone,email);
        emailInvalid(email);
        System.out.println(email);
        if(isThisCompanyInRepository == null){
            Contact transportContact = conctactService.saveContact(name,phone,email);
            TransportCompany newTransportCompany = new TransportCompany(name,transportContact);
            transportCompany = newTransportCompany;
        }
        if(isThisCompanyInRepository != null){
            throw new DuplicatesException("El transporte ya se encuentra en el sistema");
        }
        return transportCompanyRepository.save(transportCompany);
    }
    public void nameOrPhoneOrEmailInvalid( String name, String phone,String email)  {
        if(name.isEmpty() || name == null || phone.isEmpty() || phone == null || email.isEmpty() || email == null || !email.contains("@") || !email.contains(".com") || email.contains("@.") || !email.contains(".")){
            throw new ValidationException("Los datos ingresados no son validos");
        }

    };

    public void emailInvalid(String email){
        long repetitionsCount = email.chars().filter(ch -> ch == '@').count();
        System.out.println(repetitionsCount);

        if(repetitionsCount >1){
            throw new ValidationException("El mail ingresado es invalido");
        }
    }

    @Override
    public List<TransportCompany> getAllTransportCompanies() {
        List<TransportCompany> companiesInSystem = (List<TransportCompany>) transportCompanyRepository.findAll();
        return companiesInSystem;
    }

    @Override
    public TransportCompany getCompanyByName(String name) {
        TransportCompany transportCompany = transportCompanyRepository.findByName(name);
        TransportCompany transportCompanyFinded = null;
        if (transportCompany != null) {
                transportCompanyFinded = transportCompany;
        }
        return transportCompanyFinded;
    }

    @Override
    public void deleteById(Long id) {
        TransportCompany transportCompany = getTransportById(id);
        transportCompanyRepository.delete(transportCompany);
    }

    @Override
    public TransportCompany getTransportById(Long id) {
        return transportCompanyRepository.findById(id).orElseThrow(()->new TransportCompanyNotFound(id));
    }
}
