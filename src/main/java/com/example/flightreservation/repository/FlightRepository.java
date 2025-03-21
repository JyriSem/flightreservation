package com.example.flightreservation.repository;

import com.example.flightreservation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT DISTINCT f.destination FROM Flight f WHERE f.departure = :departure")
    List<String> findDestinationsByDeparture(@Param("departure") String departure);

    @Query("SELECT DISTINCT f.departureDate FROM Flight f WHERE f.departure = :departure AND f.destination = :destination ORDER BY f.departureDate ASC")
    List<LocalDate> findAvailableDates(@Param("departure") String departure, @Param("destination") String destination);

    List<Flight> findByDepartureAndDestinationAndDepartureDate(String departure, String destination, LocalDate departureDate);

    List<Flight> findByDepartureAndDestinationAndDepartureDateAndDepartureTimeBetween(
            String departure, String destination, LocalDate departureDate, LocalTime startTime, LocalTime endTime);
}
