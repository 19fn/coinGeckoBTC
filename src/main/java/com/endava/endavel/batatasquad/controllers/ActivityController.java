package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.domain.Destination;
import com.endava.endavel.batatasquad.services.IActivityService;
import com.endava.endavel.batatasquad.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/batatasquad/activity")
public class ActivityController {

    private IActivityService activityService;

    @Autowired
    public void setActivityService(IActivityService activityService) {
        this.activityService = activityService;
    }



    @GetMapping("/many")
    public ResponseEntity<List<ActivityVO>> findAllActivities(@RequestParam(required = false) Double price,
                                                        @RequestParam(required = false) Destination destId,
                                                        @RequestParam(required = false) Integer typeId){
        if(price != null){
            return new ResponseEntity<>(activityService.findActivitiesByPrice(price).stream().map((t) -> new ActivityVO(t)).collect(Collectors.toList()), HttpStatus.OK);
        }else if (destId != null){
            return new ResponseEntity<>(activityService.findActivitiesByDestId(destId).stream().map((t) -> new ActivityVO(t)).collect(Collectors.toList()), HttpStatus.OK);
//        }else if (typeId != null){
//            return new ResponseEntity<>(activityService.findActivitiesByTypeId(typeId).stream().map((t) -> new ActivityVO(t)).collect(Collectors.toList()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(activityService.findActivities().stream().map((t) -> new ActivityVO(t)).collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    //TODO: Move name and hours filter to findAllActivities method
    @GetMapping("/one")
    public ResponseEntity<ActivityVO> findActivityBy(@RequestParam (required = false) Integer id,
                                             @RequestParam (required = false) String name,
                                             @RequestParam (required = false) Double hours) {
        if(id != null){
            return new ResponseEntity<>(new ActivityVO(activityService.findActivityById(id)), HttpStatus.OK);
        }else if (name != null){
            return new ResponseEntity<>(new ActivityVO(activityService.findActivityByName(name)), HttpStatus.OK); // If there are two activities with the same name, it throws an exception
        }else if (hours != null){
            return new ResponseEntity<>(new ActivityVO(activityService.findActivityByHours(hours)), HttpStatus.OK); // If there are two activities with the same name, it throws an exception
        }else{
            return null;
        }
    }

    @PostMapping("/one")
    public ResponseEntity<ActivityVO> createActivity (@RequestBody ActivityVO activityVO){
        ActivityVO response = new ActivityVO(activityService.createActivity(activityVO));
        return ResponseEntity.ok(response);
        
    }

    @PostMapping("/many")
    public ResponseEntity<List<ActivityVO>> createActivity (@RequestBody List<ActivityVO> activities){
        List<ActivityVO> response = activityService.createActivity(activities).stream().map(ActivityVO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}




