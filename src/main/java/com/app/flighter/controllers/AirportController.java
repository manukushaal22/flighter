package com.app.flighter.controllers;

import com.app.flighter.exceptions.ResourceNotFoundException;
import com.app.flighter.models.Airport;
import com.app.flighter.models.Flight;
import com.app.flighter.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @GetMapping("/id/{id}")
    public Airport getAirportById(@PathVariable(value = "id") Long noteId) {
        return airportRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Airport", "id", airportRepository));
    }

    @GetMapping("/iata/{iata}")
    public Airport getAirportByName(@PathVariable(value = "iata") String iata) {
        return airportRepository.findByAirportName(iata);
    }

    @GetMapping("/id/{id}/arrivingFlights")
    public List<Flight> getArrivingFlightstoAirportById(@PathVariable(value = "id") Long noteId) {
        return airportRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Airport", "id", airportRepository)).getArrivingFlights();
    }

    @GetMapping("/id/{id}/departingFlights")
    public List<Flight> getDepartingFlightstoAirportById(@PathVariable(value = "id") Long noteId) {
        return airportRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Airport", "id", airportRepository)).getDepartingFlights();
    }

    @PostMapping("/create")
    public Airport createAirport(@Valid @RequestBody Airport airport){
        return airportRepository.save(airport);
    }
}
