package com.example.flightreservation.controller;

import com.example.flightreservation.model.Seat;
import com.example.flightreservation.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{flightId}")
    public List<Seat> getAvailableSeats(@PathVariable Long flightId) {
        return seatService.getAvailableSeats(flightId);
    }
}
