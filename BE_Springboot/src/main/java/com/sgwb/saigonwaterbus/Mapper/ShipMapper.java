package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.DTO.ShipDTO;
import com.sgwb.saigonwaterbus.Model.Ship;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipMapper {
    ShipMapper INSTANCE = Mappers.getMapper(ShipMapper.class);
    Ship toShip(ShipDTO shipDTO);
    ShipDTO toShipDto(Ship ship);
    List<Ship> toShipList(List<ShipDTO> shipDTOList);
    List<ShipDTO> toShipDtoList(List<Ship> shipList);
}
