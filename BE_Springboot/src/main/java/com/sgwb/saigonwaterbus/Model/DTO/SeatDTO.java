package com.sgwb.saigonwaterbus.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    private Long id;
    private Long ship_id;
    private String status;
    private String seatName;
    private LocalDate createAt;
    private LocalDate deleteAt;
    private LocalDate updateAt;
}
