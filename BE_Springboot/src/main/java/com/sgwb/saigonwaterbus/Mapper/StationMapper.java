package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.DTO.StationDTO;
import com.sgwb.saigonwaterbus.Model.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationMapper INSTANCE = Mappers.getMapper(StationMapper.class);
    Station toStation(StationDTO stationDTO);
    @Mapping(source = "station.addressStation", target = "address")

    StationDTO toStationDTO(Station station);
    List<Station> toStationList(List<StationDTO> stationDTOList);
    List<StationDTO> toStationDtoList(List<Station> stationList);
}
