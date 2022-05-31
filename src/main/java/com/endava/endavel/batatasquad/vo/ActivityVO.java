package com.endava.endavel.batatasquad.vo;

import com.endava.endavel.batatasquad.domain.Activity;

import lombok.Getter;

@Getter
public class ActivityVO {

    public Integer id;

    public String name;

    public String description;
   
    public Double hours;

    public Integer availableFromMonth;

    public Integer availableToMonth;

    public Double price;

    public Integer contactId;

    public Integer destinationId;

    public Integer timebandId;

    public Integer typeId;

    public ActivityVO(){
    }

    public ActivityVO(Activity activity) {
        this.id = activity.getId();
        this.contactId = null; // activity.getContId().getId();
        this.destinationId = null; // activity.getDestId().getId();
        this.timebandId = null; // activity.getTimeband().getId();
        this.typeId = null; //activity.getTypeActivity().getId();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.hours = activity.getHours();
        this.price = activity.getPrice();
        this.availableFromMonth = activity.getAvailableFromMonth();
        this.availableToMonth = activity.getAvailableToMonth();
    }


}
