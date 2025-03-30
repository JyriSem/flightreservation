package com.example.flightreservation.service;

import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    public List<Seat> getAvailableSeats(Long flightId) {
        return seatRepository.findByFlightIdAndOccupiedFalse(flightId);
    }
}
