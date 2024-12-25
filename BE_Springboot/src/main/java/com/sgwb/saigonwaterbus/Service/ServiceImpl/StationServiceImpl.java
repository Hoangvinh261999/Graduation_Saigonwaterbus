package com.sgwb.saigonwaterbus.Service.ServiceImpl;


import com.sgwb.saigonwaterbus.Dao.StationDao;
import com.sgwb.saigonwaterbus.Mapper.StationMapper;
import com.sgwb.saigonwaterbus.Model.DTO.StationDTO;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Station;
import com.sgwb.saigonwaterbus.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {
    @Autowired
    private StationDao stationDao;

    @Override
    public Station saveStation(StationDTO stationDTO) {
        Station station = Station.builder()
                .name(stationDTO.getName())
                .addressStation(stationDTO.getAddress())
                .status(Status.ACTIVE)
                .createAt(LocalDate.now())
                .build();
        return stationDao.save(station);
    }

    @Override
    public boolean updateStation(StationDTO stationDTO) {
        Station station= stationDao.findStationById(stationDTO.getId());
        if (stationDao.existsById(Math.toIntExact(stationDTO.getId()))) {
            station.setName(stationDTO.getName());
            station.setAddressStation(stationDTO.getAddress());
            station.setStatus(stationDTO.getStatus());
            station.setCreateAt(stationDTO.getCreate_at());
            station.setUpdateAt(LocalDate.now());
            stationDao.save(station);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStation(Long id) {
        int result = stationDao.softDeleteStation(id);
        return result>0;
    }

    @Override
    public StationDTO findStationByID(Long id) {
        return StationMapper.INSTANCE.toStationDTO(stationDao.findStationById(id));
    }

    @Override
    public Page<StationDTO> findAll(Pageable pageable) {
        Page<Station> stationPage = stationDao.findAll(pageable);
        return stationPage.map(StationMapper.INSTANCE::toStationDTO);
    }
}

