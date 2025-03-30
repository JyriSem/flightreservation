package com.example.flightreservation.repository;

import com.example.flightreservation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//Kohandatud päringumeetodid, vastavalt nimele genereeritakse automaat-päringud signatuuri põhjal.
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    //Lähtelinnade loend.
    List<Flight> findByDeparture(String departure);

    //Lähte- ja sihtlinnade loend.
    List<Flight> findByDepartureAndDestination(String departure, String destination);

    //Lähtelinnade, sihtlinnade ja väljumiskuupäevade loend.
    List<Flight> findByDepartureAndDestinationAndDepartureDate(String departure, String destination, LocalDate departureDate);
}
