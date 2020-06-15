package com.app.flighter.services;

import com.app.flighter.models.Traveller;

import java.util.List;

public interface TravellerService {
    List<Traveller> getTravellersOfFlight(Long flightId);
}
