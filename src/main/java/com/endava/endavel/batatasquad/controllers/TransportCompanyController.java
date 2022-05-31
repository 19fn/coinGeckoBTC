package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.services.ITransportCompanyService;
import com.endava.endavel.batatasquad.domain.TransportCompany;
import com.endava.endavel.batatasquad.vo.TransportCompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/batatasquad/transportCompany")
public class TransportCompanyController {

    @Autowired
    private final ITransportCompanyService iTransportCompanyService;

    public TransportCompanyController(ITransportCompanyService iTransportCompanyService) {
        this.iTransportCompanyService = iTransportCompanyService;
    }


    @GetMapping
    public ResponseEntity<Object> getCompanies(@RequestParam(required = false) String name){
        if(name != null){
            TransportCompany transportCompany = iTransportCompanyService.getCompanyByName(name);
            TransportCompanyVO response = new TransportCompanyVO(transportCompany);
            return ResponseEntity.ok(response);
        }
        List<TransportCompany> allCompanies = iTransportCompanyService.getAllTransportCompanies();
        List<TransportCompanyVO> response = allCompanies.stream().map(TransportCompanyVO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response); // new ResponseEntity(allCompanies,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransportCompanyVO> saveAnTransportCompany(@RequestBody TransportCompanyVO transportCompanyVO){
        TransportCompany transportCompany= iTransportCompanyService.addTransportCompany(transportCompanyVO.name, transportCompanyVO.transportContact.phone, transportCompanyVO.transportContact.email);
        TransportCompanyVO response = new TransportCompanyVO(transportCompany);
        return ResponseEntity.ok(response); // new ResponseEntity("Transport company " + response + " saved ", HttpStatus.OK);
    }

    @DeleteMapping("/{transportId}")
    public ResponseEntity<String> deleteAnTransportCompany(@PathVariable Long transportId){
        TransportCompany transportCompany = iTransportCompanyService.getTransportById(transportId);
        iTransportCompanyService.deleteById(transportId);
        TransportCompanyVO response = new TransportCompanyVO(transportCompany);
        return ResponseEntity.ok("Transport company " + response.id + " has been deleted.");

    }
}
