package com.sgwb.saigonwaterbus.Mapper;


import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.DTO.AccountDTO;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.RegisterResponse;
import com.sgwb.saigonwaterbus.Model.DTO.TicketDTO;
import com.sgwb.saigonwaterbus.Model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    Ticket toTicket(TicketDTO ticketDTO);
    TicketDTO toTicketDTO(Ticket ticket);
    List<Ticket> toTicketList(List<TicketDTO> ticketDTOList);
    List<TicketDTO> toTicketDtoList(List<Ticket> ticketList);
}

