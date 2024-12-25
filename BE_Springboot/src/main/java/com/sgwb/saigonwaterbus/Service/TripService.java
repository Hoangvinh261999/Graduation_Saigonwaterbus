package com.sgwb.saigonwaterbus.Service;

import com.sgwb.saigonwaterbus.Model.DTO.TripCRUD;
import com.sgwb.saigonwaterbus.Model.DTO.TripDTO;
import com.sgwb.saigonwaterbus.Model.Trip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface TripService {
    List<TripDTO> findTrips(Long fromTerminalId, Long toTerminalId, LocalDate departureDate);
    Trip saveTrip(TripCRUD tripCRUD);

    boolean updateTrip(TripCRUD tripCRUD);
    TripCRUD findTripByID(Long id);
    boolean deleteTripById(Long id);
    public Page<TripCRUD> findAllTrip(Pageable pageable);
    Trip findTripByid(Long id);

    Page<TripCRUD> findAllByDepartureDate(LocalDate departureDate, Pageable pageable);
}
