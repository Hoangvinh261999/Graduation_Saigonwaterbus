package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sgwb.saigonwaterbus.Dao.RouteDao;
import com.sgwb.saigonwaterbus.Dao.ShipDao;
import com.sgwb.saigonwaterbus.Dao.TripDao;
import com.sgwb.saigonwaterbus.Mapper.TripMapper;
import com.sgwb.saigonwaterbus.Model.*;
import com.sgwb.saigonwaterbus.Model.DTO.SeatDTO;
import com.sgwb.saigonwaterbus.Model.DTO.TripCRUD;
import com.sgwb.saigonwaterbus.Model.DTO.TripDTO;
import com.sgwb.saigonwaterbus.Model.Enum.PayMethod;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public
class TripServiceImpl implements TripService {
    @Autowired
    private TripDao tripDao;
    @Autowired
    private RouteDao routeDao;
    @Autowired
    private ShipDao shipDao;

    @Override
    @Transactional(readOnly = true)
    public List<TripDTO> findTrips(Long fromTerminalId, Long toTerminalId, LocalDate departureDate) {
        List<Object[]> results = tripDao.findTripByRouteAndDate(fromTerminalId, toTerminalId, departureDate);
        return results.stream().map(this::convertToTripDTO).collect(Collectors.toList());
    }

    private TripDTO convertToTripDTO(Object[] result) {
        Long id = ((Number) result[0]).longValue();
        int totalSeats = ((Number) result[1]).intValue();

        LocalDate departureDate = ((java.sql.Date) result[2]).toLocalDate();

        String departureTime = (String) result[3];
        String arrivalTime = (String) result[4];
        String fromTerminal = (String) result[5];
        String toTerminal = (String) result[6];
        String startTerminal = (String) result[7];
        String endTerminal = (String) result[8];
        int availableSeats = ((Number) result[9]).intValue();
        String addressStart = (String) result[10];
        String addressEnd = (String) result[11];

        return new TripDTO(
                id,
                totalSeats,
                departureDate,
                departureTime,
                arrivalTime,
                fromTerminal,
                toTerminal,
                startTerminal,
                endTerminal,
                availableSeats,
                addressStart,
                addressEnd
        );
    }

    @Override
    public Trip saveTrip(TripCRUD tripCRUD) {
        Route getRoute = routeDao.findRouteById(tripCRUD.getRoute().getId());
        Ship getShip = shipDao.findShipById(tripCRUD.getShip().getId());
        Trip data = Trip.builder()
                .departureDate(tripCRUD.getDepartureDate())
                .departureTime(tripCRUD.getDepartureTime())
                .arrivalTime(tripCRUD.getArrivalTime())
                .availableSeats(tripCRUD.getAvailableSeats())
                .route(getRoute)
                .ship(getShip)
                .status(tripCRUD.getStatus())
                .fixed(tripCRUD.getFixed())
                .createAt(LocalDate.now())
                .build();
        return tripDao.save(data);
    }

    @Override
    public boolean updateTrip(TripCRUD tripCRUD) {
        if (tripDao.existsById(tripCRUD.getId())) {
            Route getRoute = routeDao.findRouteById(tripCRUD.getRoute().getId());
            Ship getShip = shipDao.findShipById(tripCRUD.getShip().getId());
            Trip trip = tripDao.findTripById(tripCRUD.getId());

            trip.setDepartureDate(tripCRUD.getDepartureDate());
            trip.setDepartureTime(tripCRUD.getDepartureTime());
            trip.setArrivalTime(tripCRUD.getArrivalTime());
            trip.setAvailableSeats(tripCRUD.getAvailableSeats());
            trip.setRoute(getRoute);
            trip.setShip(getShip);
            trip.setStatus(tripCRUD.getStatus());
            trip.setFixed(tripCRUD.getFixed());
            trip.setCreateAt(tripCRUD.getCreateAt());
            trip.setUpdateAt(LocalDate.now());

            tripDao.save(trip);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TripCRUD findTripByID(Long id) {
        return TripMapper.INSTANCE.toTripCrud(tripDao.findTripById(id));
    }

    @Override
    public boolean deleteTripById(Long id) {
        int rowsAffected = tripDao.softDeleteTrip(id);
        return rowsAffected > 0;
    }

    @Override
    public Page<TripCRUD> findAllByDepartureDate(LocalDate departureDate, Pageable pageable) {
        Page<Trip> tripCRUDPage = tripDao.findAllByDepartureDate(departureDate, pageable);
        return tripCRUDPage.map(TripMapper.INSTANCE::toTripCrud);
    }
    @Override
    public Page<TripCRUD> findAllTrip(Pageable pageable) {
        Page<Trip> tripCRUDPage1 = tripDao.findAll(pageable);
        return tripCRUDPage1.map(TripMapper.INSTANCE::toTripCrud);
    }

    @Override
    public Trip findTripByid(Long id) {
        return tripDao.findTripById(id);
    }

}
