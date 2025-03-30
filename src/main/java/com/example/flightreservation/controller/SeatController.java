package com.example.flightreservation.controller;

import com.example.flightreservation.model.Seat;
import com.example.flightreservation.service.SeatService;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RESTful veebiteenusekontroller / käsitleb HTTP-päringuid.
@RestController

//Endpoint
@RequestMapping("/api/seats")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {

    //Äriloogikad "service" klassides.
    private final SeatService seatService;
    private final SeatRepository seatRepository;

    //Lennu vabad istekohad.
    @GetMapping("/{flightId}")
    public List<Seat> getAvailableSeats(@PathVariable Long flightId) {
        return seatService.getAvailableSeats(flightId);
    }

    //Lennu istekohtade loend "flightId" alusel.
    @GetMapping("/flight/{flightId}")
    public List<Seat> getSeatsByFlight(@PathVariable Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }
}
