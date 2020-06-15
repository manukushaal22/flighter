package com.app.flighter.services;

import com.app.flighter.models.Flight;

import java.util.List;

public interface TravellerService {
    List<Flight> getFlightsOfTraveller(Long travellerId);
}
