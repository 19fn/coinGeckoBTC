package com.endava.endavel.batatasquad.domain;

import com.endava.endavel.batatasquad.vo.ActivityVO;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)

    private Integer id;
    private String name;
    private String description;
    private Double hours;
    private Double price;
    private Integer availableFromMonth;
    private Integer availableToMonth;

    @ManyToOne
    @JoinColumn(name ="type_id")
    private TypeActivity typeActivity;

    @ManyToOne
    @JoinColumn(name ="time_id")
    private Timeband timeband;

    @ManyToOne
    @JoinColumn(name ="dest_id")
    private Destination destId;

    @ManyToOne
    private Contact contId;

    public Activity(Integer id, String name, String description, Double hours, Double price, Integer availableFromMonth,
                    Integer availableToMonth, Destination destId, TypeActivity typeActivity, Timeband timeband, Contact contId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.price = price;
        this.availableFromMonth = availableFromMonth;
        this.availableToMonth = availableToMonth;
        this.destId = destId;
        this.typeActivity = typeActivity;
        this.timeband = timeband;
        this.contId = contId;
    }


    public Activity(ActivityVO activityVO, Destination destination, TypeActivity typeActivity, Timeband timeband, Contact contact) {
        //this.id = activityVO.getId();
        this.destId = destination;
        this.contId = contact;
        this.destId = destination;
        this.typeActivity = typeActivity;
        this.name = activityVO.getName();
        this.description = activityVO.getDescription();
        this.hours = activityVO.getHours();
        this.price = activityVO.getPrice();
        this.availableFromMonth = activityVO.getAvailableFromMonth();
        this.availableToMonth= activityVO.getAvailableToMonth();

    }
}
