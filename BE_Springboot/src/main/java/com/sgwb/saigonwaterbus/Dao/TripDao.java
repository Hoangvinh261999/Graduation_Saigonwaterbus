package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TripDao extends JpaRepository<Trip, Long> {
    Page<Trip> findAllByDepartureDate(LocalDate departureDate, Pageable pageable);

    @Procedure(name = "FindTripByRouteAndDate")
    List<Object[]> findTripByRouteAndDate(
            @Param("fromTerminalId") Long fromTerminalId,
            @Param("toTerminalId") Long toTerminalId,
            @Param("departureDate") LocalDate departureDate
    );
    Trip findTripById(Long id);
    @Modifying
    @Transactional
    @Query(value = "update Trip set status = 'INACTIVE', delete_at = CURRENT_TIMESTAMP where id = :id", nativeQuery = true)
    int softDeleteTrip(@Param("id") Long id);

    @Query(value = "SELECT TOP 1 trip.departure_date FROM trip ORDER BY trip.departure_date", nativeQuery = true)
    LocalDate findOldestDate();

    @Query(value = "SELECT TOP 1 trip.departure_date FROM trip ORDER BY trip.departure_date DESC", nativeQuery = true)
    LocalDate findNewestDate();
    List<Trip> findAllByDepartureDate(LocalDate date);
}
