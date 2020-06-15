package com.app.flighter.services;

import com.app.flighter.models.Flight;
import com.app.flighter.models.Traveller;
import com.app.flighter.models.TravellerFlight;

import java.util.List;

public interface FlightService {
    List<Traveller> getTravellersOfFlight(Long flightId);
    TravellerFlight addTravellerToFlight(Long flightId, Long travellerId, TravellerFlight travellerFlight);
    Flight createFlight(Flight flight);
}
