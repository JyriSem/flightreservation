package com.example.flightreservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//Andmebaasi kaardistamine.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    //Tabeli "flight" primaarvõti.
    @Id

    //Andmebaasi automaatne suurendamine.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Igale lennule kodrumatu identifikaator / ja teised veerud.
    private Long id;
    private String departure;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private BigDecimal price;

    //Üks-mitmele seos lennu ja istme(-te) vahel / haldamine "mappedBy" ja manipuleerimine "cascade" abil.
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Seat> seats;
}
