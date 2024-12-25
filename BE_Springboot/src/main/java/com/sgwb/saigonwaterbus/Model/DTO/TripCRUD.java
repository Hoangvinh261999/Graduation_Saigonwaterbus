package com.sgwb.saigonwaterbus.Model.DTO;

import com.sgwb.saigonwaterbus.Model.Enum.Fixed;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripCRUD {
    private Long id;
    private LocalDate departureDate;
    private String departureTime;
    private String arrivalTime;
    private Integer availableSeats;
    private RouteDTO route;
    private ShipDTO ship;
    private Status status;
    private Fixed fixed;
    private LocalDate createAt;
    private LocalDate updateAt;
    private LocalDate deleteAt;
}
