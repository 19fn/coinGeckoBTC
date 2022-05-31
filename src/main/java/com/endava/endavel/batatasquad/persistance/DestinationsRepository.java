package com.endava.endavel.batatasquad.persistance;

import com.endava.endavel.batatasquad.domain.Destination;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DestinationsRepository extends CrudRepository<Destination, Integer> {
    Optional<Destination> getDestinationByCityId(Integer cityId);
}
