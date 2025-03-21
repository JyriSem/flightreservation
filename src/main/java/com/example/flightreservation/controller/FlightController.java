package com.example.flightreservation.controller;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.repository.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/departures")
    public List<String> getDepartures() {
        return flightRepository.findAll()
                .stream()
                .map(Flight::getDeparture)
                .distinct()
                .toList();
    }

    @GetMapping("/destinations")
    public List<String> getDestinations(@RequestParam String departure) {
        return flightRepository.findDestinationsByDeparture(departure);
    }

    @GetMapping("/dates")
    public List<LocalDate> getAvailableDates(@RequestParam String departure, @RequestParam String destination) {
        return flightRepository.findAvailableDates(departure, destination);
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam LocalDate date) {
        return flightRepository.findByDepartureAndDestinationAndDepartureDate(departure, destination, date);
    }
}
