package com.sgwb.saigonwaterbus.Model.DTO;

import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.RouteWaypoints;
import com.sgwb.saigonwaterbus.Model.Station;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDTO {
    private Long id;
    private String nameRoute;
    private Station fromTerminal;
    private Station toTerminal;
    private List<RouteWaypoints> waypoints;
    private Status status;
    private LocalDate createAt;
    private LocalDate updateAt;
    private LocalDate deleteAt;
}
