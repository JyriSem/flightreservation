package com.example.flightreservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//Andmebaasi kaardistamine.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    //Tabeli "flight" primaarvõti.
    @Id

    //Andmebaasi automaatne suurendamine.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Igale istmele kodrumatu identifikaator / ja teised veerud.
    private Long id;
    private int rowNumber;
    private String columnLetter;
    private boolean occupied;
    private boolean windowSeat;
    private boolean exitRow;
    private boolean extraLegroom;
    private BigDecimal price;

    //Istmete sidumine lennuga, kasutades "foreign key-d" / ringviite "List<Seat> vältimine.
    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnore
    private Flight flight;
}
