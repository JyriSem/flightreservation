package com.example.flightreservation.config;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    @Override
    public void run(String... args) {
        if (flightRepository.count() == 0) {
            Flight flight1 = new Flight(null, "NYC", "LAX", LocalDateTime.now().plusDays(3), 300.00, new ArrayList<>());
            Flight flight2 = new Flight(null, "LON", "PAR", LocalDateTime.now().plusDays(2), 120.00, new ArrayList<>());
            flight1 = flightRepository.save(flight1);
            flight2 = flightRepository.save(flight2);
            createSeatsForFlight(flight1);
            createSeatsForFlight(flight2);
        }
    }

    private void createSeatsForFlight(Flight flight) {
        List<Seat> seats = new ArrayList<>();
        char[] seatColumns = {'A', 'B', 'C'};
        int totalRows = 22;

        for (int row = 1; row <= totalRows; row++) {
            for (char column : seatColumns) {
                boolean isOccupied = Math.random() < 0.3; // 30% of seats randomly occupied
                seats.add(new Seat(null, row, column, isOccupied, flight));
            }
        }
        seatRepository.saveAll(seats);
    }
}
