package com.sgwb.saigonwaterbus.Service;

import com.sgwb.saigonwaterbus.Model.DTO.TicketDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;

public interface TicketService {
    Page<TicketDTO> getPaginatedTickets(int page, int size, LocalDate date);
    TicketDTO getTotalTicketByDay();
    TicketDTO countAllCustomer();
    Page<TicketDTO> getAllTickets(Pageable pageable);
    boolean existsByIdAndStatus(Long id, LocalDate date);

}
