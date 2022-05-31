package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.domain.TransportTicket;
import com.endava.endavel.batatasquad.services.ITransportTicketService;
import com.endava.endavel.batatasquad.vo.TransportTicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/batatasquad/transportTicket")
public class TransportTicketController {

    @Autowired
    private final ITransportTicketService iTransportTicketService;


    public TransportTicketController(ITransportTicketService iTransportTicketService) {
        this.iTransportTicketService = iTransportTicketService;
    }

    @GetMapping
    public ResponseEntity<List<TransportTicketVO>> getAllTickets(){
        List<TransportTicket> transportTickets = iTransportTicketService.getAllTickets();
        List<TransportTicketVO> response = transportTickets.stream().map(TransportTicketVO::new).collect(Collectors.toList()); 
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TransportTicketVO> makeReservation(@RequestBody TransportTicketVO transportTicketVO){
        TransportTicket reservation = iTransportTicketService.makeReservation(transportTicketVO.transportCompany, transportTicketVO.description, transportTicketVO.price, transportTicketVO.fromCity, transportTicketVO.toDestination);
        TransportTicketVO response = new TransportTicketVO(reservation);
        return ResponseEntity.ok(response); // new ResponseEntity("The reservation has been done " + response,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnReservation(@PathVariable Long id){
        iTransportTicketService.deleteReservation(id);
        return ResponseEntity.ok("The ticket " + id + " has been deleted");
    }
}
