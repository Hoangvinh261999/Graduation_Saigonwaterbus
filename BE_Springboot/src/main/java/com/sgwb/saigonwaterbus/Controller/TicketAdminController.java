package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Dao.TicketDao;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.TicketDTO;
import com.sgwb.saigonwaterbus.Model.Ticket;
import com.sgwb.saigonwaterbus.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus/admin")
public class TicketAdminController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    TicketDao ticketDao;
    @GetMapping("/tickets")
    public ResponseEntity<ApiResponse<Page<TicketDTO>>> getPaginatedTickets(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "20") int size,
                                                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        ApiResponse<Page<TicketDTO>> response = new ApiResponse<>();
        try {
            Page<TicketDTO> ticketDTOPage = ticketService.getPaginatedTickets(page, size, date);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all tickets success");
            response.setResult(ticketDTOPage);
            if (ticketDTOPage.isEmpty()) {

                response.setCode(204);
                response.setMessage("No tickets found");
                response.setResult(ticketDTOPage);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/ticketAlls")
    public ResponseEntity<ApiResponse<Page<TicketDTO>>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<Page<TicketDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<TicketDTO> ticketdtolist = ticketService.getAllTickets(pageable);
            response.setCode(1005);
            response.setMessage("Success");
            response.setResult(ticketdtolist);
            if (ticketdtolist.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("No ticket found");
                response.setResult(null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Failed to fetch stations: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("tickets/{ticketId}")
    public ResponseEntity<ApiResponse<TicketDTO>> findbyID(@PathVariable("ticketId") Long id) {
        ApiResponse<TicketDTO> response = new ApiResponse<>();
        try {
            // Fetch ticket entity
            Ticket ticket = ticketDao.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));

            // Create TicketDTO with required fields
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setId(ticket.getId());
            ticketDTO.setDepartureDate(ticket.getDepartureDate());
            ticketDTO.setPrice(ticket.getPrice());
            ticketDTO.setStatus(ticket.getStatus());
            ticketDTO.setCreateAt(ticket.getCreateAt());
            ticketDTO.setUpdateAt(ticket.getUpdateAt());
            ticketDTO.setDeleteAt(ticket.getDeleteAt());

            // Include additional details if available
            if (ticket.getSeat() != null) {
                ticketDTO.setSeatName(ticket.getSeat().getSeatName()); // Assuming Seat has a getName() method
            }
            if (ticket.getTrip() != null) {
                ticketDTO.setTripid(String.valueOf(ticket.getTrip().getId())); // Assuming Trip has a getName() method
            }
            if (ticket.getInvoice() != null) {
                ticketDTO.setInvoiceId(ticket.getInvoice().getId()); // Assuming Invoice has an getId() method
            }

            response.setCode(HttpStatus.OK.value());
            response.setMessage("Ticket found");
            response.setResult(ticketDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
