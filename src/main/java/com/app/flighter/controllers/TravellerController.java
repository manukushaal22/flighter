package com.app.flighter.controllers;

import com.app.flighter.exceptions.ResourceNotFoundException;
import com.app.flighter.models.Flight;
import com.app.flighter.models.Traveller;
import com.app.flighter.repositories.TravellerRepository;
import com.app.flighter.services.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/travellers")
public class TravellerController {

    private final TravellerService travellerService;

    private final TravellerRepository travellerRepository;

    @Autowired
    public TravellerController(TravellerService travellerService, TravellerRepository travellerRepository) {
        this.travellerService = travellerService;
        this.travellerRepository = travellerRepository;
    }

    @GetMapping("/id/{id}")
    public Traveller getTraveller(@PathVariable(value = "id") Long travellerId){
        return travellerRepository.findById(travellerId).orElseThrow(() -> new ResourceNotFoundException("Traveller", "id", travellerId));
    }

    @GetMapping("/id/{travellerId}/flights")
    public List<Flight> getFlightsByTraveller(@PathVariable(value = "travellerId") Long travellerId){
        return travellerService.getFlightsOfTraveller(travellerId);
    }

    @PostMapping("/create")
    public Traveller createTraveller(@Valid @RequestBody Traveller traveller){
        return travellerRepository.save(traveller);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteTraveller(@PathVariable(value = "id") Long travellerId) {
        Traveller traveller = travellerRepository.findById(travellerId).orElseThrow(() -> new ResourceNotFoundException("Traveller", "id", travellerId));

        travellerRepository.delete(traveller);

        return ResponseEntity.ok().build();
    }
}
