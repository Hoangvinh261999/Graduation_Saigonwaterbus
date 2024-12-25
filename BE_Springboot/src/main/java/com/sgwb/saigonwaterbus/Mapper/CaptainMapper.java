package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.Captain;
import com.sgwb.saigonwaterbus.Model.DTO.CaptainDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CaptainMapper {
    CaptainMapper INSTANCE = Mappers.getMapper(CaptainMapper.class);
    Captain toCaptain(CaptainDTO captainDTO);
    @Mapping(source = "captain.ship.id", target = "shipId")

    CaptainDTO toCaptainDto(Captain captain);
    List<Captain> toCaptainList(List<CaptainDTO> captainDTOList);
    List<CaptainDTO> toCaptainDtoList(List<Captain> captainList);
}
