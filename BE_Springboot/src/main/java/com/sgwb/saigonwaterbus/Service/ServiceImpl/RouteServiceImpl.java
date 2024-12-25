package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.sgwb.saigonwaterbus.Dao.RouteDao;
import com.sgwb.saigonwaterbus.Dao.RouteWaypointsDao;
import com.sgwb.saigonwaterbus.Dao.StationDao;
import com.sgwb.saigonwaterbus.Mapper.RouteMapper;
import com.sgwb.saigonwaterbus.Model.DTO.RouteDTO;
import com.sgwb.saigonwaterbus.Model.Route;
import com.sgwb.saigonwaterbus.Model.RouteWaypoints;
import com.sgwb.saigonwaterbus.Model.Station;
import com.sgwb.saigonwaterbus.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteDao routeDao;

    @Autowired
    private StationDao stationDao;

    @Autowired
    private RouteWaypointsDao routeWaypointsDao;

    @Override
    public Page<RouteDTO> findAll(Pageable pageable) {
        Page<Route> routePage = routeDao.findAll(pageable);
        return routePage.map(RouteMapper.INSTANCE::toRouteDto);
    }

    @Override
    public RouteDTO findRouteById(Long id) {
        return RouteMapper.INSTANCE.toRouteDto(routeDao.findRouteById(id));
    }

    @Override
    public boolean deleteRouteById(Long id) {
        int rowsAffected = routeDao.softDeleteRoute(id);
        return rowsAffected > 0;
    }

    @Override
    @Transactional
    public Route saveRouteWithWaypoints(RouteDTO routeDTO) {
        Station fromTerminal = stationDao.findStationById(routeDTO.getFromTerminal().getId());
        Station toTerminal = stationDao.findStationById(routeDTO.getToTerminal().getId());
        Route saveRoute = Route.builder()
                .nameRoute(routeDTO.getNameRoute())
                .fromTerminal(fromTerminal)
                .toTerminal(toTerminal)
                .status(routeDTO.getStatus())
                .createAt(LocalDate.now())
                .waypoints(new ArrayList<>())
                .build();
        routeDao.save(saveRoute);
        if (routeDTO.getWaypoints() != null) {
            for (RouteWaypoints routeWaypoints : routeDTO.getWaypoints()) {
                RouteWaypoints saveRouteWaypoints = RouteWaypoints.builder()
                        .route(saveRoute)
                        .station(stationDao.findStationById(routeWaypoints.getStation().getId()))
                        .stopOrder(routeWaypoints.getStopOrder())
                        .build();
                routeWaypointsDao.save(saveRouteWaypoints);
                saveRoute.getWaypoints().add(saveRouteWaypoints);
            }
        }
        return saveRoute;
    }

    @Override
    public boolean updateRouteWithWaypoints(RouteDTO routeDTO) {
        if (routeDao.existsById(routeDTO.getId())) {
            Station fromTerminal = stationDao.findStationById(routeDTO.getFromTerminal().getId());
            Station toTerminal = stationDao.findStationById(routeDTO.getToTerminal().getId());
            Route route=routeDao.findRouteById(routeDTO.getId());
            route.setNameRoute(routeDTO.getNameRoute());
            route.setFromTerminal(fromTerminal);
            route.setToTerminal(toTerminal);
            route.setStatus(routeDTO.getStatus());
            route.setCreateAt(routeDTO.getCreateAt());
            route.setUpdateAt(LocalDate.now());
            routeDao.save(route);
            routeWaypointsDao.deleteByRoute(route);
            if (routeDTO.getWaypoints() != null) {
                List<RouteWaypoints> updatedWaypoints = new ArrayList<>();
                for (RouteWaypoints routeWaypoints : routeDTO.getWaypoints()) {
                    RouteWaypoints saveRouteWaypoints = RouteWaypoints.builder()
                            .id(routeWaypoints.getId())
                            .route(route)
                            .station(stationDao.findStationById(routeWaypoints.getStation().getId()))
                            .stopOrder(routeWaypoints.getStopOrder())
                            .build();
                    routeWaypointsDao.save(saveRouteWaypoints);
                    updatedWaypoints.add(saveRouteWaypoints);
                }
                route.setWaypoints(updatedWaypoints);
            }
            routeDao.save(route);
            return true;
        } else {
            return false;
        }
    }
}
