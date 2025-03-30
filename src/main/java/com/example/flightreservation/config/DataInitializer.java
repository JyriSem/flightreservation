package com.example.flightreservation.config;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final Random random = new Random();

    @PostConstruct
    public void initData() {
        if (flightRepository.count() == 0) {
            generateFlights();
        }
    }

    private void generateFlights() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(null, "New York", "Los Angeles", LocalDate.now().plusDays(1), LocalTime.of(8, 30), BigDecimal.valueOf(26400.0), new ArrayList<>()));
        flights.add(new Flight(null, "New York", "Los Angeles", LocalDate.now().plusDays(1), LocalTime.of(10, 30), BigDecimal.valueOf(26400.0), new ArrayList<>()));
        flights.add(new Flight(null, "New York", "Chicago", LocalDate.now().plusDays(2), LocalTime.of(10, 45), BigDecimal.valueOf(18000.0), new ArrayList<>()));
        flights.add(new Flight(null, "Chicago", "San Francisco", LocalDate.now().plusDays(3), LocalTime.of(15, 20), BigDecimal.valueOf(30000.0), new ArrayList<>()));
        flights.add(new Flight(null, "Los Angeles", "Miami", LocalDate.now().plusDays(4), LocalTime.of(12, 15), BigDecimal.valueOf(28000.0), new ArrayList<>()));
        flightRepository.saveAll(flights);
        flights.forEach(this::createSeatsForFlight);
    }

    private void createSeatsForFlight(Flight flight) {
        List<Seat> seats = new ArrayList<>();
        char[] seatColumns = {'A', 'B', 'C', 'D', 'E', 'F'};
        int totalRows = 22;
        BigDecimal totalSeats = BigDecimal.valueOf(totalRows * seatColumns.length);
        BigDecimal basePrice = flight.getPrice().divide(totalSeats, 2, RoundingMode.HALF_UP);

        for (int row = 1; row <= totalRows; row++) {
            for (char column : seatColumns) {
                boolean occupied = random.nextDouble() < 0.3;
                boolean windowSeat = (column == 'A' || column == 'F');
                boolean exitRow = (row == 10 || row == 11);
                boolean extraLegroom = (row == 1 || row == 10 || row == 11);
                BigDecimal seatPrice = basePrice;
                if (windowSeat) seatPrice = seatPrice.add(BigDecimal.valueOf(10.0));
                if (exitRow) seatPrice = seatPrice.add(BigDecimal.valueOf(20.0));
                if (extraLegroom && !exitRow) seatPrice = seatPrice.add(BigDecimal.valueOf(15.0));
                seats.add(new Seat(null, row, String.valueOf(column), occupied, windowSeat, exitRow, extraLegroom, seatPrice, flight));
            }
        }
        seatRepository.saveAll(seats);
    }
}