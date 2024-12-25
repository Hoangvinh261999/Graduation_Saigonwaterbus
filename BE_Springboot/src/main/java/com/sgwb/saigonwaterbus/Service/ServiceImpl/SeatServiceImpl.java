package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.sgwb.saigonwaterbus.Dao.SeatDao;
import com.sgwb.saigonwaterbus.Dao.ShipDao;
import com.sgwb.saigonwaterbus.Mapper.SeatMapper;
import com.sgwb.saigonwaterbus.Model.DTO.SeatDTO;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Seat;
import com.sgwb.saigonwaterbus.Model.Ship;
import com.sgwb.saigonwaterbus.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private ShipDao shipDao;

    @Override
    public List<Seat> findByShipTripId(Long tripId) {
        List<Seat> seatList = seatDao.findByShipTripId(tripId);
        return seatList;
    }

    @Override
    public List<Seat> getSeat(Long tripId, LocalDate departureDate) {
        List<Seat> seatStatusList = seatDao.getSeatStatus(tripId, departureDate);
        return seatStatusList;
    }

    @Override
    public Seat savaSeat(SeatDTO seatDTO) {
        Ship ship = shipDao.findShipById(seatDTO.getShip_id());
        Seat data = Seat.builder()
                .ship(ship)
                .seatName(seatDTO.getSeatName())
                .status(Status.ACTIVE)
                .createAt(LocalDate.now())
                .build();
        return seatDao.save(data);
    }

    @Override
    public List<Seat> saveAll(List<Seat> seatList) {
        return seatDao.saveAll(seatList);
    }

    @Override
    public boolean updateSeat(SeatDTO seatDTO) {
        if (seatDao.existsById(seatDTO.getId())) {
            Seat data = seatDao.findSeatById(seatDTO.getId());
            if(data.getStatus()==Status.ACTIVE){
                data.setStatus(Status.INACTIVE);
            }else{
                data.setStatus(Status.ACTIVE);
            }
            seatDao.save(data);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteSeat(Long id) {
        int result = seatDao.softDeleteSeat(id);
        return result>0;
    }

    @Override
    public Page<SeatDTO> findAll(Pageable pageable) {
        Page<Seat> seatPage = seatDao.findAll(pageable);
        return seatPage.map(SeatMapper.INSTANCE::toSeatDto);
    }

    @Override
    public SeatDTO findSeatByID(Long id) {
        return SeatMapper.INSTANCE.toSeatDto(seatDao.findSeatById(id));
    }
}
