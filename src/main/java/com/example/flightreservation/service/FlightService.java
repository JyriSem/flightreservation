package com.example.flightreservation.service;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> searchFlights(String departure, String destination) {
        return flightRepository.findByDepartureAndDestination(departure, destination);
    }
}
