package com.sgwb.saigonwaterbus.Service;


import com.sgwb.saigonwaterbus.Model.DTO.StationDTO;
import com.sgwb.saigonwaterbus.Model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StationService {
    Station saveStation(StationDTO stationDTO);
    boolean updateStation(StationDTO stationDTO);
    boolean deleteStation(Long id);
    StationDTO findStationByID(Long id);
    Page<StationDTO> findAll(Pageable pageable);
}
