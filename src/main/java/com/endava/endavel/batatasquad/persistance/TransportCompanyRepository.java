package com.endava.endavel.batatasquad.persistance;

import com.endava.endavel.batatasquad.domain.TransportCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportCompanyRepository extends CrudRepository<TransportCompany, Long> {
    TransportCompany findByName(String name);
}
