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

//RESTful veebiteenusekontroller / käsitleb HTTP-päringuid.
@RestController

//Endpoint
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    //Äriloogika "service" klassis.
    private final FlightService flightService;

    //Lähtelinnade loend.
    @GetMapping("/departures")
    public ResponseEntity<List<String>> getDepartures() {
        return ResponseEntity.ok(flightService.getAllDepartures());
    }

    //Sihtlinnade loend.
    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getDestinations(@RequestParam String departure) {
        return ResponseEntity.ok(flightService.getDestinationsForDeparture(departure));
    }

    //Ainult saadaolevate lennukuupäevade loend.
    @GetMapping("/dates")
    public ResponseEntity<List<LocalDate>> getAvailableDates(
            @RequestParam String departure,
            @RequestParam String destination) {
        return ResponseEntity.ok(flightService.getAvailableDates(departure, destination));
    }

    //Kindlatele kriteeriumitele vastav otsing / kuupäeva vormindamine.
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(flightService.findFlights(departure, destination, date));
    }

    //Valitud lennu ("flightId" alusel) istekohatade andmed.
    @GetMapping("/{flightId}/seats")
    public ResponseEntity<List<Seat>> getSeats(@PathVariable Long flightId) {
        return ResponseEntity.ok(flightService.getSeatsForFlight(flightId));
    }
}
