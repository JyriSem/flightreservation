package com.example.flightreservation.service;

import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//Äriloogika.
@Service
@RequiredArgsConstructor
public class SeatService {

    //Päringute tegemiseks ja andmetega manipuleerimiseks.
    private final SeatRepository seatRepository;

    //Hõivamata istekohtade loend "flightId" alusel.
    public List<Seat> getAvailableSeats(Long flightId) {
        return seatRepository.findByFlightIdAndOccupiedFalse(flightId);
    }
}
