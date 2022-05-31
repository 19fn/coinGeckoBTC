package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Insurance;
import java.util.List;

public interface IInsuranceService {

    List<Insurance> findAllInsurances();

    //public List<Insurance> findAllInsurances();
    Insurance findInsuranceById(Long id);
    Insurance saveInsurance(Integer contactId, String name, String description, Double pricePerDay);
    List<Insurance> findInsuranceByPricePerDay(Double pricePerDay);
    void deleteInsuranceById(Long id);

}

