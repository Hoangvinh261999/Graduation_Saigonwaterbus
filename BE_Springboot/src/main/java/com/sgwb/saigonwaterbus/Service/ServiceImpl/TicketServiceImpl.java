package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.sgwb.saigonwaterbus.Dao.TicketDao;
import com.sgwb.saigonwaterbus.Mapper.TicketMapper;
import com.sgwb.saigonwaterbus.Model.DTO.TicketDTO;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Ticket;
import com.sgwb.saigonwaterbus.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketDao ticketDao;
    @Override
    public Page<TicketDTO> getPaginatedTickets(int page, int size, LocalDate date) {
        Page<Ticket> tickets = ticketDao.findByCreateAt(date, PageRequest.of(page,size));
        List<TicketDTO> ticketDTOs = tickets.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(ticketDTOs, tickets.getPageable(), tickets.getTotalElements());
    }
    @Override
    public Page<TicketDTO> getAllTickets(Pageable pageable) {
        Page<Ticket> ticketsPage = ticketDao.findAll(pageable);
        return ticketsPage.map(TicketMapper.INSTANCE::toTicketDTO);
    }

    @Override
    public boolean existsByIdAndStatus(Long id, LocalDate date) {
        List<Ticket> tickets = ticketDao.findByIdAndDepartureDateAndStatus(id, date);
        if (tickets != null && !tickets.isEmpty()) {
            return true;
        }
        return false;
    }



    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setDepartureDate(ticket.getDepartureDate());
        dto.setPrice(ticket.getPrice());
        dto.setStatus(ticket.getStatus());
        dto.setCreateAt(ticket.getCreateAt());
        dto.setUpdateAt(ticket.getUpdateAt());
        dto.setDeleteAt(ticket.getDeleteAt());
        return dto;
    }

    @Override
    public TicketDTO getTotalTicketByDay() {
        Object result = ticketDao.getTotalTicketByDay();
        TicketDTO ticketDTO = TicketDTO.builder().totalTicket((Integer) result).build();
        return ticketDTO;
    }

    @Override
    public TicketDTO countAllCustomer() {
        Object result = ticketDao.countAllCustomer();
        TicketDTO ticketDTO = TicketDTO.builder().totalTicket((Integer) result).build();
        return ticketDTO;
    }

}