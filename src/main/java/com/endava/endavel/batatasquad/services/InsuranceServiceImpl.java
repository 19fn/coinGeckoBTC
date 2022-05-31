package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Contact;
import com.endava.endavel.batatasquad.domain.Insurance;
import com.endava.endavel.batatasquad.exceptions.InsuranceNotFoundException;
import com.endava.endavel.batatasquad.persistance.InsuranceRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class InsuranceServiceImpl implements IInsuranceService {
    private InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public List<Insurance> findAllInsurances() {
        return (List<Insurance>) insuranceRepository.findAll();
    }

    @Override
    public Insurance findInsuranceById(Long id) {
        return insuranceRepository.findById(id).orElseThrow(() -> new InsuranceNotFoundException("Insurance with id "+ id +" not found"));
    }
    @Override
    public Insurance saveInsurance(Integer contactId, String name, String description, Double pricePerDay) {
        //TODO: Find contact by id
        Contact contact = null; //contactservice.findById(contactId);
        Insurance insurance = new Insurance(contact, name, description, pricePerDay);
        return insuranceRepository.save(insurance);
    }
    @Override
    public List<Insurance> findInsuranceByPricePerDay(Double pricePerDay) {
        return insuranceRepository.findInsuranceByPricePerDay(pricePerDay);
    }

    @Override
    public void deleteInsuranceById(Long id) {
        Insurance insurance = findInsuranceById(id);
        insuranceRepository.delete(insurance);
        //return insuranceRepository.deleteInsuranceById(id);
    }

   }
