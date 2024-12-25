package com.sgwb.saigonwaterbus.Model.DTO;

import com.sgwb.saigonwaterbus.Model.Station;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteWaypointsDTO {
    private Long id;
    private Station station;
    private Integer stopOrder;
}
