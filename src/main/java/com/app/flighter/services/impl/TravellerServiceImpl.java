package com.app.flighter.services.impl;

import com.app.flighter.exceptions.ResourceNotFoundException;
import com.app.flighter.models.Flight;
import com.app.flighter.models.Traveller;
import com.app.flighter.models.TravellerFlight;
import com.app.flighter.repositories.FlightRepository;
import com.app.flighter.services.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravellerServiceImpl implements TravellerService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Traveller> getTravellersOfFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(()->new ResourceNotFoundException("flight","id",flightId));
        List<Traveller> travellersList = new ArrayList<>();
        for(TravellerFlight travellerFlight : flight.getTravellerFlights()){
            travellersList.add(travellerFlight.getTraveller());
        }
        return travellersList;
    }
}
