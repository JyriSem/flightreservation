package com.example.flightreservation.service;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String departure, String destination, LocalDate date) {
        return flightRepository.findByDepartureAndDestinationAndDepartureDate(departure, destination, date);
    }

    public List<Flight> searchFlightsWithTime(
            String departure, String destination, LocalDate date, LocalTime startTime, LocalTime endTime) {
        if (startTime != null && endTime != null) {
            return flightRepository.findByDepartureAndDestinationAndDepartureDateAndDepartureTimeBetween(
                    departure, destination, date, startTime, endTime);
        } else {
            return searchFlights(departure, destination, date);
        }
    }
}
