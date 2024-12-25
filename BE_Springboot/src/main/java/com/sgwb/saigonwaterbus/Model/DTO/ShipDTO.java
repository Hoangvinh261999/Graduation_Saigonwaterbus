package com.sgwb.saigonwaterbus.Model.DTO;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipDTO {
    private Long id;
    private Integer totalSeats;
    private Status status;
    private LocalDate createAt;
    private LocalDate updateAt;
    private LocalDate deleteAt;
    private String numberPlate;
}