package com.sgwb.saigonwaterbus.Model.DTO;

import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class TicketDTO {

    private Long id;
    private LocalDate departureDate;
    private Double price;
    private Status status;
    private LocalDate createAt;
    private LocalDate updateAt;
    private LocalDate deleteAt;
    private String seatName;
    private String tripid;
    private Long invoiceId;
    private int totalTicket;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.departureDate = ticket.getDepartureDate();
        this.price = ticket.getPrice();
        this.status = ticket.getStatus();
        this.createAt = ticket.getCreateAt();
        this.updateAt = ticket.getUpdateAt();
        this.deleteAt = ticket.getDeleteAt();
        if (ticket.getSeat() != null) {
            this.seatName = ticket.getSeat().getSeatName();
        }
        if (ticket.getTrip() != null) {
            this.tripid  = String.valueOf(ticket.getTrip().getId());
        }
        if (ticket.getInvoice() != null) {
            this.invoiceId = ticket.getInvoice().getId();
        }
    }
}