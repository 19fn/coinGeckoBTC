package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.services.DestinationsService;
import com.endava.endavel.batatasquad.vo.DestinationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/batatasquad/destination")
public class DestinationsController {
    private final DestinationsService destinationsService;

    @Autowired
    public DestinationsController(DestinationsService destinationsService) {
        this.destinationsService = destinationsService;
    }

    @GetMapping
    public ResponseEntity<List<DestinationVO>> getAllDestinations(){
        return ResponseEntity.ok(convertDestinationsToVOS(destinationsService.getAllDestinations()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationVO> getDestinationById(@PathVariable Integer id) {
        return ResponseEntity.ok(convertDestinationToVO(destinationsService.getDestinationById(id)));
    }

    @PostMapping
    public ResponseEntity<DestinationVO> saveDestination(@RequestBody DestinationVO destinationVO) {
        return new ResponseEntity<>(
                convertDestinationToVO(destinationsService.saveDestination(destinationVO)), HttpStatus.CREATED);
    }

    @PostMapping("/many")
    public ResponseEntity<List<DestinationVO>> saveAllDestinations(@RequestBody List<DestinationVO> destinationsVOS) {
        return new ResponseEntity<>(
                convertDestinationsToVOS(destinationsService.saveAllDestinations(destinationsVOS)), HttpStatus.CREATED);
    }

    private List<DestinationVO> convertDestinationsToVOS(List<Destination> destinations) {
        return destinations.stream()
                .map(this::convertDestinationToVO)
                .collect(Collectors.toList());
    }

    private DestinationVO convertDestinationToVO(Destination destination) {
        return new DestinationVO(destination);
    }
}