package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.sgwb.saigonwaterbus.Dao.ShipDao;
import com.sgwb.saigonwaterbus.Mapper.ShipMapper;
import com.sgwb.saigonwaterbus.Model.DTO.ShipDTO;
import com.sgwb.saigonwaterbus.Model.Ship;
import com.sgwb.saigonwaterbus.Service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipDao shipDao;

    @Override
    public Ship saveShip(ShipDTO shipDTO) {
        if(shipDao.existsByNumberPlate(shipDTO.getNumberPlate())) {
            throw new DataIntegrityViolationException("Biển số tàu đã tồn tại!");
        }

        Ship data = Ship.builder()
                .totalSeats(shipDTO.getTotalSeats())
                .status(shipDTO.getStatus())
                .createAt(LocalDate.now())
                .numberPlate(shipDTO.getNumberPlate())
                .build();
        return shipDao.save(data);
    }

    @Override
    public ShipDTO updateShip(ShipDTO shipDTO) {
        if(!shipDTO.getNumberPlate().equalsIgnoreCase(shipDao.findShipById(shipDTO.getId()).getNumberPlate())&&shipDao.existsByNumberPlate(shipDTO.getNumberPlate())) {
            throw new DataIntegrityViolationException("Biển số tàu đã tồn tại!");
        }

        if(!shipDao.existsById(shipDTO.getId())) {
            throw new DataIntegrityViolationException("Tàu không tồn tại!");
        }
        Ship updateShip = shipDao.findShipById(shipDTO.getId());
        updateShip.setId(shipDTO.getId());
        updateShip.setTotalSeats(shipDTO.getTotalSeats());
        updateShip.setStatus(shipDTO.getStatus());
        updateShip.setCreateAt(shipDTO.getCreateAt());
        updateShip.setUpdateAt(LocalDate.now());
        updateShip.setNumberPlate(shipDTO.getNumberPlate());
        shipDao.save(updateShip);
        return ShipMapper.INSTANCE.toShipDto(updateShip);


    }

    @Override
    public boolean deleteShip(Long id) {
        int rowResult = shipDao.softDeleteShip(id);
        return rowResult>0;
    }

    @Override
    public ShipDTO findShipById(Long id) {
        return ShipMapper.INSTANCE.toShipDto(shipDao.findShipById(id));
    }

    @Override
    public Page<ShipDTO> findAll(Pageable pageable) {
        Page<Ship> shipPage = shipDao.findAll(pageable);
        return shipPage.map(ShipMapper.INSTANCE::toShipDto);
    }
}