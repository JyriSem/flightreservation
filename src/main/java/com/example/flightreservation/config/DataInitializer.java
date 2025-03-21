package com.example.flightreservation.config;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final Random random = new Random();

    public DataInitializer(FlightRepository flightRepository, SeatRepository seatRepository) {
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }

    @PostConstruct
    public void initData() {
        if (flightRepository.count() == 0) {
            List<Flight> flights = new ArrayList<>();

            flights.add(new Flight(null, "New York", "Los Angeles", LocalDate.now().plusDays(3), LocalTime.of(10, 30), 250.00, new ArrayList<>()));
            flights.add(new Flight(null, "New York", "Los Angeles", LocalDate.now().plusDays(3), LocalTime.of(15, 45), 230.00, new ArrayList<>()));
            flights.add(new Flight(null, "New York", "Chicago", LocalDate.now().plusDays(5), LocalTime.of(12, 15), 180.00, new ArrayList<>()));
            flights.add(new Flight(null, "Los Angeles", "Chicago", LocalDate.now().plusDays(7), LocalTime.of(9, 0), 200.00, new ArrayList<>()));
            flights.add(new Flight(null, "Los Angeles", "New York", LocalDate.now().plusDays(10), LocalTime.of(20, 30), 270.00, new ArrayList<>()));

            flightRepository.saveAll(flights);

            for (Flight flight : flights) {
                createSeatsForFlight(flight);
            }

            System.out.println("Database initialized with sample flights and seats.");
        }
    }

    private void createSeatsForFlight(Flight flight) {
        List<Seat> seats = new ArrayList<>();
        char[] seatColumns = {'A', 'B', 'C', 'D', 'E', 'F'};
        int totalRows = 22;

        for (int row = 1; row <= totalRows; row++) {
            for (char column : seatColumns) {
                boolean occupied = random.nextDouble() < 0.3;
                boolean windowSeat = (column == 'A' || column == 'F');
                boolean exitRow = (row == 10 || row == 11);
                boolean extraLegroom = exitRow;

                seats.add(new Seat(null, row, String.valueOf(column), occupied, windowSeat, exitRow, extraLegroom, flight));
            }
        }
        seatRepository.saveAll(seats);
    }
}
