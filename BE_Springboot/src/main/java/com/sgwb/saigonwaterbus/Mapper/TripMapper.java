package com.sgwb.saigonwaterbus.Mapper;

import com.sgwb.saigonwaterbus.Model.DTO.TripCRUD;
import com.sgwb.saigonwaterbus.Model.DTO.TripDTO;
import com.sgwb.saigonwaterbus.Model.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper (componentModel = "spring")
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);
    TripCRUD toTripCrud(Trip trip);
    Trip toTrip(TripCRUD tripCRUD);
    List<Trip> toTripList(List<TripCRUD> tripCRUDList);
    List<TripCRUD> toTripCrudList(List<Trip> tripList);
}
