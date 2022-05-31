package com.endava.endavel.batatasquad.services;
import com.endava.endavel.batatasquad.domain.TransportCompany;

import java.util.List;


public interface ITransportCompanyService {
    TransportCompany addTransportCompany(String name, String phone, String email);

    List<TransportCompany> getAllTransportCompanies();

    TransportCompany getCompanyByName(String name);

    void deleteById(Long id);
    
    TransportCompany getTransportById(Long id);

}
