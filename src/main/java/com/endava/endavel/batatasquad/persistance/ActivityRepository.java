package com.endava.endavel.batatasquad.persistance;
import com.endava.endavel.batatasquad.domain.Activity;
import com.endava.endavel.batatasquad.domain.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer>{
    List<Activity> findAll();
    List<Activity> findActivitiesByDestId(Destination destId);
    List<Activity> findActivitiesByPrice(Double price);
    //List<Activity> findActivitiesByTypeId (Integer typeId);

    Activity findActivityById(Integer id);
    Activity findActivityByName(String name);
    Activity findActivityByDescription(String description);

    Activity findActivityByHours(Double hours);
    Activity findActivityByAvailableFromMonth(Integer availableFromMonth);
    Activity findActivityByAvailableToMonth(Integer availableToMonth);

}
