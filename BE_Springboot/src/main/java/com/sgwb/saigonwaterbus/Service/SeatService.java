package com.sgwb.saigonwaterbus.Service;

import com.sgwb.saigonwaterbus.Model.DTO.SeatDTO;
import com.sgwb.saigonwaterbus.Model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SeatService {
    List<Seat> findByShipTripId(Long tripId);
    List<Seat> getSeat(Long tripId, LocalDate departureDate);
    Seat savaSeat(SeatDTO seatDTO);
    List<Seat> saveAll(List<Seat> seatList);
    boolean updateSeat(SeatDTO seatDTO);
    boolean deleteSeat(Long id);
    Page<SeatDTO> findAll(Pageable pageable);
    SeatDTO findSeatByID(Long id);
}
