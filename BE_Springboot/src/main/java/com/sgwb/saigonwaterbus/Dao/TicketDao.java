package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Seat;
import com.sgwb.saigonwaterbus.Model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TicketDao extends JpaRepository<Ticket, Long> {
    Page<Ticket> findByCreateAt(@Param("departureDate") LocalDate departureDate, Pageable pageable);
    @Query(value = "select count(*) as ve from ticket where delete_at IS NULL AND  CAST(create_at AS DATE) = CAST(GETDATE() AS DATE);", nativeQuery = true)
    Object getTotalTicketByDay();
    @Query (value = "select count(*) as khach from ticket where delete_at IS NULL;", nativeQuery = true)
    Object countAllCustomer();
    @Query (value = "select count(*) as khach from ticket where delete_at IS NULL;", nativeQuery = true)
    Seat checkticket();

//    List<Ticket> existsByIdAndStatus(Long id, Status status,);
@Query("SELECT t FROM Ticket t WHERE t.seat.id = :id AND t.departureDate = :departureDate AND (t.status = 'BOOKED' OR t.status = 'INPROGRESS')")
List<Ticket> findByIdAndDepartureDateAndStatus(@Param("id") Long id,
                                               @Param("departureDate") LocalDate departureDate);


}
