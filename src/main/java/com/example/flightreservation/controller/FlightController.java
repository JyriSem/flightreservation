package com.example.flightreservation.controller;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam String departure, @RequestParam String destination) {
        return flightService.searchFlights(departure, destination);
    }
}
