package com.sgwb.saigonwaterbus.Mapper;
import com.sgwb.saigonwaterbus.Model.DTO.SeatDTO;
import com.sgwb.saigonwaterbus.Model.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);
    @Mapping(source = "seat.id", target = "id")
    @Mapping(source = "seat.ship.id", target = "ship_id")
    @Mapping(source = "seat.status", target = "status")
    SeatDTO toSeatDto(Seat seat);
    Seat toSeat(SeatDTO seatDTO);
    @Mapping(source = "seat.id", target = "id")
    @Mapping(source = "seat.ship.id", target = "ship_id")
    @Mapping(source = "seat.status", target = "status")

    List<Seat> toSeatList(List<SeatDTO> seatDTOList);
    List<SeatDTO> toSeatDtoList(List<Seat> seatList);
}
