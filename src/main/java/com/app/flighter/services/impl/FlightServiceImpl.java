package com.app.flighter.services.impl;

import com.app.flighter.exceptions.ResourceNotFoundException;
import com.app.flighter.models.Flight;
import com.app.flighter.models.Traveller;
import com.app.flighter.models.TravellerFlight;
import com.app.flighter.repositories.AirportRepository;
import com.app.flighter.repositories.FlightRepository;
import com.app.flighter.repositories.TravellerFlightRepository;
import com.app.flighter.repositories.TravellerRepository;
import com.app.flighter.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final TravellerRepository travellerRepository;

    private final TravellerFlightRepository travellerFlightRepository;

    private final AirportRepository airportRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, TravellerRepository travellerRepository, TravellerFlightRepository travellerFlightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.travellerRepository = travellerRepository;
        this.travellerFlightRepository = travellerFlightRepository;
        this.airportRepository = airportRepository;
    }


    @Override
    public List<Traveller> getTravellersOfFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(()->new ResourceNotFoundException("flight","id",flightId));
        List<Traveller> travellersList = new ArrayList<>();
        for(TravellerFlight travellerFlight : flight.getTravellerFlights()){
            travellersList.add(travellerFlight.getTraveller());
        }
        return travellersList;
    }

    @Override
    public TravellerFlight addTravellerToFlight(Long flightId, Long travellerId, TravellerFlight travellerFlight) {
        travellerFlight.setFlight(flightRepository.findById(travellerId).orElseThrow(()->new ResourceNotFoundException("flight","id",flightId)));
        travellerFlight.setTraveller(travellerRepository.findById(travellerId).orElseThrow(()->new ResourceNotFoundException("traveller","id",travellerId)));
        return travellerFlightRepository.save(travellerFlight);
    }

    @Override
    public Flight createFlight(Flight flight){
        flight.setSourceAirport(airportRepository.findByAirportName(flight.getSourceAirport().getAirportName()));
        flight.setDestinationAirport(airportRepository.findByAirportName(flight.getDestinationAirport().getAirportName()));
        return flightRepository.save(flight);
    }
}
