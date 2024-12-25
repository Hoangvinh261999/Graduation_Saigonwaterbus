package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.DTO.RouteDTO;
import com.sgwb.saigonwaterbus.Model.DTO.RouteWaypointsDTO;
import com.sgwb.saigonwaterbus.Model.Route;
import com.sgwb.saigonwaterbus.Model.RouteWaypoints;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);
    RouteDTO toRouteDto(Route route);
    Route toRoute(RouteDTO routeDTO);
    List<Route> toRouteList(List<RouteDTO> routeDTOList);
    List<RouteDTO> toRouteDtoList(List<Route> routeList);
}
