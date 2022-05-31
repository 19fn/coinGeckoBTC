package com.endava.endavel.batatasquad.services;


import com.endava.endavel.batatasquad.domain.*;
import com.endava.endavel.batatasquad.domain.Activity;
import com.endava.endavel.batatasquad.exceptions.NotFoundException;
import com.endava.endavel.batatasquad.persistance.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.endava.endavel.batatasquad.vo.ActivityVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements IActivityService{

    private ActivityRepository activityRepository;


    @Autowired
    public void setActivityRepository(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> findActivitiesByDestId(Destination destId) {
        return activityRepository.findActivitiesByDestId(destId);
    }

    @Override
    public List<Activity> findActivitiesByPrice(Double price) {
        return activityRepository.findActivitiesByPrice(price);
    }

//    @Override
//    public List<Activity> findActivitiesByTypeId(Integer type){
//        return activityRepository.findActivitiesByTypeId(type);
//    }

    @Override
    public List<Activity> findActivitiesByTimeId(Integer timeId) {
        return null;
    }

    @Override
    public List<Activity> findActivitiesByContId(Integer contId) {
        return null;
    }

    @Override
    public List<Activity> findActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity findActivityById(Integer id) {
        return activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity with id " + id + " not found"));
    }

    @Override
    public Activity findActivityByName(String name) {
        return activityRepository.findActivityByName(name);
    }

    @Override
    public Activity findActivityByDescription(String description) {
        return activityRepository.findActivityByDescription(description);
    }

    @Override
    public Activity findActivityByAvailableFromToMonth(Integer available_from_month) {
        return activityRepository.findActivityByAvailableFromMonth(available_from_month);
    }

    @Override
    public Activity findActivityByHours(Double hours) {
        return activityRepository.findActivityByHours(hours);
    }

    @Override
    public Activity findActivityByAvailableToMonth(Integer available_to_month) {
        return activityRepository.findActivityByAvailableToMonth(available_to_month);
    }

    @Override
    public Activity createActivity (ActivityVO activityVO) {

        //TODO: find a contact using activityVO.contId
        Contact contact = null;

        //TODO: find a destination using activityVO.destId
        Destination destination = null;

        //TODO: find a type using activityVO.typeId
        TypeActivity type = null;

        //TODO: find a timeband using activityVO.timeId
        Timeband timeband = null;

        Activity activity = new Activity(activityVO, destination, type, timeband, contact);
        return activityRepository.save(activity);
    }


    @Override
    public List<Activity> createActivity(List<ActivityVO> activities) {
        //TODO: Validate VO before and find the corresponding objects
        return (List<Activity>) activityRepository.saveAll(
            activities.stream().map(t -> new Activity(t, null, null, null, null)).collect(Collectors.toList())
        );
    }}








