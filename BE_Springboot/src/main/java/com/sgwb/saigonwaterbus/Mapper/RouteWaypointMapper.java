package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.DTO.RouteWaypointsDTO;
import com.sgwb.saigonwaterbus.Model.RouteWaypoints;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteWaypointMapper {
    RouteWaypointMapper INSTANCE = Mappers.getMapper(RouteWaypointMapper.class);
    RouteWaypointsDTO toRouteWaypointDto(RouteWaypoints routeWaypoints);
    RouteWaypoints toRouteWaypoints(RouteWaypointsDTO routeWaypointsDTO);
    List<RouteWaypoints> toRouteWaypointsList(List<RouteWaypointsDTO> routeWaypointsDTOList);
    List<RouteWaypointsDTO> toRouteWaypointDtoList(List<RouteWaypoints> routeWaypointsList);
}
