package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Seat;
import com.sgwb.saigonwaterbus.Model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface SeatDao extends JpaRepository<Seat, Long> {
        Seat findSeatById(Long id);
        @Modifying
        @Transactional
        @Query(value = "update Seat set status = 'INACTIVE', delete_at = CURRENT_TIMESTAMP where id = :id", nativeQuery = true)
        int softDeleteSeat(@Param("id") Long id);
        @Query(nativeQuery = true,
                value = "SELECT se.* " +
                        "FROM seat se " +
                        "INNER JOIN ship sh ON se.ship_id = sh.id " +
                        "INNER JOIN trip tr ON sh.id = tr.ship_id " +
                        "WHERE tr.id = :tripId")
        List<Seat> findByShipTripId(@Param("tripId") Long tripId);
        @Query(value = "SELECT s.* FROM seat s INNER JOIN ticket t ON s.id = t.seatid WHERE t.tripid = :tripId AND t.departure_date = :departureDate", nativeQuery = true)
        List<Seat> getSeatStatus(@Param("tripId") Long tripId, @Param("departureDate") LocalDate departureDate);

        @Query(value = "SELECT s.* FROM seat s " +
                "INNER JOIN ticket t ON s.id = t.seatid " +
                "WHERE t.tripid = :tripId AND t.departure_date = :departureDate",
                nativeQuery = true)
        List<Seat> findSeatsByTripIdAndDepartureDate(@Param("tripId") Long tripId, @Param("departureDate") LocalDate departureDate);
        List<Seat> findSeatByShip(Ship ship);
}
