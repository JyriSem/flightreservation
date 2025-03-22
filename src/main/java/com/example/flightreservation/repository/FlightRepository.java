package com.example.flightreservation.repository;

import com.example.flightreservation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDeparture(String departure);

    List<Flight> findByDepartureAndDestination(String departure, String destination);

    List<Flight> findByDepartureAndDestinationAndDepartureDate(String departure, String destination, LocalDate departureDate);
}
