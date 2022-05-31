package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.City;
import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.domain.TransportCompany;
import com.endava.endavel.batatasquad.domain.TransportTicket;
import com.endava.endavel.batatasquad.exceptions.TransportTicketNotFound;
import com.endava.endavel.batatasquad.exceptions.ValidationException;
import com.endava.endavel.batatasquad.persistance.TransportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportTicketService implements  ITransportTicketService{

    private TransportTicketRepository transportTicketRepository;

    private ITransportCompanyService transportCompanyService;

    @Autowired
    public void setTransportTicketRepository(TransportTicketRepository transportTicketRepository) {
        this.transportTicketRepository = transportTicketRepository;
    }

    @Autowired
    public void setTransportCompanyService(ITransportCompanyService iTransportCompanyService) {
        this.transportCompanyService = iTransportCompanyService;
    }



    @Override
    public TransportTicket makeReservation(Long transportCompanyId, String description, double price, Long fromCityId, Long toDestinationId) {
        
        TransportCompany transportCompany = transportCompanyService.getTransportById(transportCompanyId);

        // TODO: Find the cities using the ids when CityService is implemented
        idOrDescriptionNotValid(transportCompanyId,description, String.valueOf(price));

        City fromCity = null; // CityService.getCityById(fromCityId);

        Destination toDestination = null; // DestinationService.getDestinationById(toDestinationId);

        TransportTicket transportTicket = new TransportTicket(transportCompany, description, price,fromCity, toDestination);
        return transportTicketRepository.save(transportTicket);
    }

    public void idOrDescriptionNotValid(Long transportCompanyId,String description,String price){
        if(transportCompanyId == null || description.isEmpty() || description == null || price.isEmpty() || price == null ){
            throw new ValidationException("Los datos ingresados no son validos");
        }
    }

    @Override
    public void deleteReservation(Long id) {
        TransportTicket transportTicket = transportTicketRepository.findById(id).orElseThrow(() -> new TransportTicketNotFound(id)); //.findByIdAndFromCityAndToDestination(id,fromCity,toDestination);
        transportTicketRepository.delete(transportTicket);
    }

    @Override
    public List<TransportTicket> getAllTickets() {
        List<TransportTicket> allTicketsInSystem = (List<TransportTicket>) transportTicketRepository.findAll();
        return allTicketsInSystem;
    }

}
