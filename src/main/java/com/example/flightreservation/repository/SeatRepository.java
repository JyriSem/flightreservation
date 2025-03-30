package com.example.flightreservation.repository;

import com.example.flightreservation.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Kohandatud päringumeetodid, vastavalt nimele genereeritakse automaat-päringud signatuuri põhjal.
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    //Istekohtade loend "flightId" alusel.
    List<Seat> findByFlightId(Long flightId);

    //Saadaval olevate stekohtade loend "flightId" alusel.
    List<Seat> findByFlightIdAndOccupiedFalse(Long flightId);
}
