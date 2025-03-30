package com.example.flightreservation.controller;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/departures")
    public ResponseEntity<List<String>> getDepartures() {
        return ResponseEntity.ok(flightService.getAllDepartures());
    }

    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getDestinations(@RequestParam String departure) {
        return ResponseEntity.ok(flightService.getDestinationsForDeparture(departure));
    }

    @GetMapping("/dates")
    public ResponseEntity<List<LocalDate>> getAvailableDates(
            @RequestParam String departure,
            @RequestParam String destination) {
        return ResponseEntity.ok(flightService.getAvailableDates(departure, destination));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(flightService.findFlights(departure, destination, date));
    }

    @GetMapping("/{flightId}/seats")
    public ResponseEntity<List<Seat>> getSeats(@PathVariable Long flightId) {
        return ResponseEntity.ok(flightService.getSeatsForFlight(flightId));
    }
}
