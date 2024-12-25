package com.sgwb.saigonwaterbus.Service;

import com.sgwb.saigonwaterbus.Model.DTO.RouteDTO;
import com.sgwb.saigonwaterbus.Model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RouteService {
    Route saveRouteWithWaypoints(RouteDTO routeDTO);
    boolean updateRouteWithWaypoints(RouteDTO routeDTO);
    Page<RouteDTO> findAll(Pageable pageable);
    RouteDTO findRouteById(Long id);
    boolean deleteRouteById(Long id);
}
