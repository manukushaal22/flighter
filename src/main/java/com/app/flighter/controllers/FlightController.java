package com.app.flighter.controllers;

import com.app.flighter.models.Flight;
import com.app.flighter.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/{travellerId}")
    public List<Flight> getFlightsByTraveller(@PathVariable(value = "travellerId") Long travellerId){
        return flightService.getFlightsOfTraveller(travellerId);
    }
}
