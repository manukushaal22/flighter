package com.app.flighter.controllers;

import com.app.flighter.exceptions.ResourceNotFoundException;
import com.app.flighter.models.Flight;
import com.app.flighter.models.Traveller;
import com.app.flighter.models.TravellerFlight;
import com.app.flighter.repositories.FlightRepository;
import com.app.flighter.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    @GetMapping("/id/{id}")
    public Flight getFlightsById(@PathVariable(value = "id") Long flightId){
        return flightRepository.findById(flightId).orElseThrow(() -> new ResourceNotFoundException("Flight", "id", flightId));
    }

    @GetMapping("/id/{flightId}/travellers")
    public List<Traveller> getTravellersByFlight(@PathVariable(value = "flightId") Long flightId){
        return flightService.getTravellersOfFlight(flightId);
    }

    @PostMapping("/id/{flightId}/addTraveller/{travellerId}")
    public TravellerFlight addTravellerToFlight(@PathVariable(value = "flightId") Long flightId, @PathVariable(value = "travellerId") Long travellerId, @Valid @RequestBody TravellerFlight travellerFlight){
        return flightService.addTravellerToFlight(flightId, travellerId, travellerFlight);
    }

    @PostMapping("/create")
    public Flight createFlight(@Valid @RequestBody Flight flight){
        return flightService.createFlight(flight);
    }
}
