package com.app.flighter.repositories;

import com.app.flighter.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query(value = "select A from Airport A where A.airportName = ?1")
    Airport findByAirportName(String airportName);
}