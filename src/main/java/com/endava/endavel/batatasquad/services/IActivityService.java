package com.endava.endavel.batatasquad.services;

import com.endava.endavel.batatasquad.domain.Activity;
import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.vo.ActivityVO;

import java.util.List;

public interface IActivityService {
        // -- List -- //
        List<Activity> findActivitiesByDestId(Destination destId);
        List<Activity> findActivitiesByPrice(Double price);
      //  List<Activity> findActivitiesByTypeId(Integer typeId);
        List<Activity> findActivitiesByTimeId(Integer timeId);
        List<Activity> findActivitiesByContId(Integer contId);
        List<Activity> findActivities();

        // -- Activity -- //
        Activity findActivityById(Integer id);
        Activity findActivityByName(String name);
        Activity findActivityByDescription(String description);
        Activity findActivityByHours(Double hours);
        Activity findActivityByAvailableFromToMonth(Integer available_from_month);
        Activity findActivityByAvailableToMonth(Integer available_to_month);

        // -- CREATED -- //
        Activity createActivity(ActivityVO activityVO);
        List<Activity> createActivity(List<ActivityVO> activitiesVO);
}
