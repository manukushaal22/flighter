package com.app.flighter.controllers;

import com.app.flighter.models.Traveller;
import com.app.flighter.services.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travellers")
public class TravellerController {

    @Autowired
    private TravellerService travellerService;

    @GetMapping("/{travellerId}")
    public List<Traveller> getTravellersByFlight(@PathVariable(value = "travellerId") Long flightId){
        return travellerService.getTravellersOfFlight(flightId);
    }
}
