package com.example.flightreservation.service;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    public List<String> getAllDepartures() {
        return flightRepository.findAll().stream()
                .map(Flight::getDeparture)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getDestinationsForDeparture(String departure) {
        return flightRepository.findByDeparture(departure).stream()
                .map(Flight::getDestination)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<LocalDate> getAvailableDates(String departure, String destination) {
        return flightRepository.findByDepartureAndDestination(departure, destination).stream()
                .map(Flight::getDepartureDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Flight> findFlights(String departure, String destination, LocalDate date) {
        return flightRepository.findByDepartureAndDestinationAndDepartureDate(departure, destination, date);
    }

    public List<Seat> getSeatsForFlight(Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }
}
