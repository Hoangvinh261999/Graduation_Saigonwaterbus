package com.sgwb.saigonwaterbus.Service;

import com.sgwb.saigonwaterbus.Model.DTO.ShipDTO;
import com.sgwb.saigonwaterbus.Model.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ShipService {
    Ship saveShip(ShipDTO shipDTO);
    ShipDTO updateShip (ShipDTO shipDTO);
    boolean deleteShip(Long id);
    ShipDTO findShipById(Long id);

    Page<ShipDTO> findAll(Pageable pageable);
}
