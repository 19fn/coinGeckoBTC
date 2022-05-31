package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.vo.InsuranceVO;
import com.endava.endavel.batatasquad.services.IInsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/batatasquad/insurance")
public class InsurancesController {
    private final IInsuranceService insuranceService;

    public InsurancesController(IInsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public ResponseEntity<List<InsuranceVO>> findAllInsurances(){
        return ResponseEntity.ok(insuranceService.findAllInsurances().stream().map(InsuranceVO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceVO> findInsuranceById(@PathVariable Long id){
        return ResponseEntity.ok(new InsuranceVO(insuranceService.findInsuranceById(id)));
    }

    @GetMapping("/price/{pricePerDay}")
    public ResponseEntity<List<InsuranceVO>> findInsuranceByPricePerDay(@PathVariable Double pricePerDay) {
        return new ResponseEntity<>(insuranceService.findInsuranceByPricePerDay(pricePerDay).stream().map(InsuranceVO::new).collect(Collectors.toList()),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInsuranceById(@PathVariable Long id) {
        insuranceService.deleteInsuranceById(id);
        return new ResponseEntity<>("Insurance with the id " + id + " has been deleted",HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InsuranceVO> saveInsurance(@RequestBody InsuranceVO insurance) {
        return ResponseEntity.ok(new InsuranceVO(insuranceService.saveInsurance(
                insurance.contactId,
                insurance.name,
                insurance.description,
                insurance.pricePerDay)));
    }

}
