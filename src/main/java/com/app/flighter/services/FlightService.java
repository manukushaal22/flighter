package com.app.flighter.services;

import com.app.flighter.models.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getFlightsOfTraveller(Long travellerId);
}
