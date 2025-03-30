package com.example.flightreservation.service;

import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//Äriloogika.
@Service
@RequiredArgsConstructor
public class FlightService {

    //Päringute tegemiseks ja andmetega manipuleerimiseks.
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    //Kordumatute lähtelinnade loend:
    //- kõikide lendude päring ja töötlemiseks ettevalmistamine;
    //- väljavõtte kaardistamine;
    //- duplikaatide välistamine;
    //- tulemuste loendisse kogumine.
    public List<String> getAllDepartures() {
        return flightRepository.findAll().stream()
                .map(Flight::getDeparture)
                .distinct()
                .collect(Collectors.toList());
    }

    //Lähtelinnna kordumatud sihtkohad:
    //- lähtelinnale vastavad sihtkohad;
    //- väljavõtte kaardistamine;
    //- duplikaatide välistamine;
    //- tulemuste loendisse kogumine.
    public List<String> getDestinationsForDeparture(String departure) {
        return flightRepository.findByDeparture(departure).stream()
                .map(Flight::getDestination)
                .distinct()
                .collect(Collectors.toList());
    }

    //Marsruut kordumatu ja sorteeritud väljumiskuupäevaga(-dega):
    //- lennud ühiste lähtelinnade ja sihtlinnadega;
    //- väljavõtte kaardistamine; dublikaatide välistamine; sorteerimine; loendissse kogumine.
    public List<LocalDate> getAvailableDates(String departure, String destination) {
        return flightRepository.findByDepartureAndDestination(departure, destination).stream()
                .map(Flight::getDepartureDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    //Lennud, mis vastavad täpsele lähte-, sihtlinna- ja kuupäevale.
    public List<Flight> findFlights(String departure, String destination, LocalDate date) {
        return flightRepository.findByDepartureAndDestinationAndDepartureDate(departure, destination, date);
    }

    //Kõik istekohad "flightId" alusel.
    public List<Seat> getSeatsForFlight(Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }
}
