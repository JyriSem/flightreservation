package com.example.flightreservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowNumber;
    private String columnLetter;
    private boolean occupied;
    private boolean windowSeat;
    private boolean exitRow;
    private boolean extraLegroom;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnore
    private Flight flight;
}