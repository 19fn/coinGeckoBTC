package com.endava.endavel.batatasquad.controllers;

import com.endava.endavel.batatasquad.exceptions.ValidationException;
import com.endava.endavel.batatasquad.services.IHotelService;
import com.endava.endavel.batatasquad.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.0/batatasquad/hotel")
public class HotelController {

    private IHotelService hotelService;

    @Autowired
    public void setHotelService(IHotelService hotelService){
        this.hotelService = hotelService;
    }

    @GetMapping("/many")
    public ResponseEntity<List<HotelVO>> findAllHotels(@RequestParam(required = false) Double pricePerDay,
                                                     @RequestParam(required = false) Integer destinationId,
                                                     @RequestParam(required = false) Integer stars) {
        return new ResponseEntity<>(hotelService.filterHotels(pricePerDay, destinationId, stars).stream().map((t) -> new HotelVO(t)).collect(Collectors.toList()), HttpStatus.OK);
    }

    //TODO: Move name and address to findAllHotels method since there could be duplicates
    @GetMapping("/one")
    public ResponseEntity<HotelVO> findHotelBy(@RequestParam(required = false) Integer id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String address) throws ValidationException {
        if(id != null){
            return new ResponseEntity<>(new HotelVO(hotelService.findHotelById(id)), HttpStatus.OK);
        }else if (name != null){
            return new ResponseEntity<>(new HotelVO(hotelService.findHotelByName(name)), HttpStatus.OK); // If there are more than one hotel with the same name, it throws an exception
        }else if (address != null){
            return new ResponseEntity<>(new HotelVO(hotelService.findHotelByAddress(address)), HttpStatus.OK); // If there are more than one hotel with the same address, it throws an exception
        }else{
            throw new ValidationException("The hotel filter doesn't exists");
        }
    }

    @PostMapping("/one")
    public ResponseEntity<HotelVO> createHotel(@RequestBody HotelVO hotelVO){
        return new ResponseEntity<>(new HotelVO(hotelService.createHotel(hotelVO)), HttpStatus.OK);

    }

    @PostMapping("/many")
    public ResponseEntity<List<HotelVO>> createHotels(@RequestBody List<HotelVO> hotels){
        return new ResponseEntity<>(hotelService.createHotels(hotels).stream().map(t -> new HotelVO(t)).collect(Collectors.toList()), HttpStatus.OK);

    }


}
