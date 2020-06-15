package com.app.flighter.services.impl;

import com.app.flighter.exceptions.ResourceNotFoundException;
import com.app.flighter.models.Flight;
import com.app.flighter.models.Traveller;
import com.app.flighter.models.TravellerFlight;
import com.app.flighter.repositories.FlightRepository;
import com.app.flighter.repositories.TravellerRepository;
import com.app.flighter.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private TravellerRepository travellerRepository;

    @Override
    public List<Flight> getFlightsOfTraveller(Long travellerId) {
        Traveller traveller = travellerRepository.findById(travellerId).orElseThrow(()->new ResourceNotFoundException("traveller","id",travellerId));
        List<Flight> flightsList = new ArrayList<>();
        for(TravellerFlight travellerFlight : traveller.getTravellerFlights()){
            flightsList.add(travellerFlight.getFlight());
        }
        return flightsList;
    }
}
