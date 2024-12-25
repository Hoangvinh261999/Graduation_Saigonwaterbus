package com.sgwb.saigonwaterbus.Model.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
    private Long id;
    private int totalSeats;
    private LocalDate departureDate;
    private String departureTime;
    private String arrivalTime;
    private String fromTerminal;
    private String toTerminal;
    private String startTerminal;
    private String endTerminal;
    private int availableSeats;
    private String addressStart;
    private String addressEnd;
}
