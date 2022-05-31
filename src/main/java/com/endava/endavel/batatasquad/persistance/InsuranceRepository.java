package com.endava.endavel.batatasquad.persistance;

import com.endava.endavel.batatasquad.domain.Insurance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
    Insurance deleteInsuranceById(Long id);
    List<Insurance> findInsuranceByPricePerDay(Double pricePerDay);
}
