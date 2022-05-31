package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.vo.DestinationVO;

import java.util.List;

public interface IDestinationsService {
    List<Destination> getAllDestinations();
    Destination getDestinationById(Integer id);
    Destination getDestinationByCityId(Integer cityId);

    Destination saveDestination(DestinationVO destinationVO);
    List<Destination> saveAllDestinations(List<DestinationVO> destinationsVOS);
}