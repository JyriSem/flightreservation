package com.example.flightreservation.controller;

import com.example.flightreservation.model.Seat;
import com.example.flightreservation.service.SeatService;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {

    private final SeatService seatService;
    private final SeatRepository seatRepository;

    @GetMapping("/{flightId}")
    public List<Seat> getAvailableSeats(@PathVariable Long flightId) {
        return seatService.getAvailableSeats(flightId);
    }

    @GetMapping("/filter")
    public List<Seat> filterSeats(
            @RequestParam Long flightId,
            @RequestParam(required = false) Boolean window,
            @RequestParam(required = false) Boolean exitRow,
            @RequestParam(required = false) Boolean extraLegroom) {

        List<Seat> seats = seatRepository.findByFlightIdAndOccupiedFalse(flightId);

        if (window != null) {
            seats = seats.stream().filter(Seat::isWindowSeat).collect(Collectors.toList());
        }
        if (exitRow != null) {
            seats = seats.stream().filter(Seat::isExitRow).collect(Collectors.toList());
        }
        if (extraLegroom != null) {
            seats = seats.stream().filter(Seat::isExtraLegroom).collect(Collectors.toList());
        }

        return seats;
    }

    @GetMapping("/flight/{flightId}")
    public List<Seat> getSeatsByFlight(@PathVariable Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }
}
