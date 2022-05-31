package com.endava.endavel.batatasquad.persistance;
import com.endava.endavel.batatasquad.domain.City;
import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.domain.TransportTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTicketRepository extends CrudRepository<TransportTicket,Long> {
    TransportTicket findByIdAndFromCityAndToDestination(Long id, City fromCity, Destination toDestination);
}
