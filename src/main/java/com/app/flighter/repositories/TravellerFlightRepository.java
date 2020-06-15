package com.app.flighter.repositories;

import com.app.flighter.models.TravellerFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravellerFlightRepository extends JpaRepository<TravellerFlight, Long> {

}