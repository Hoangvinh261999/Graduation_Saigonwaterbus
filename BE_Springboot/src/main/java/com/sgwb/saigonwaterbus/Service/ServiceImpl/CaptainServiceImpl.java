package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.sgwb.saigonwaterbus.Dao.CaptainDao;
import com.sgwb.saigonwaterbus.Dao.ShipDao;
import com.sgwb.saigonwaterbus.Mapper.CaptainMapper;
import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.Captain;
import com.sgwb.saigonwaterbus.Model.DTO.CaptainDTO;
import com.sgwb.saigonwaterbus.Model.EmailCode.EmailCodeStore;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Ship;
import com.sgwb.saigonwaterbus.Service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CaptainServiceImpl implements CaptainService {

    @Autowired
    private CaptainDao captainDao;
    @Autowired
    private ShipDao shipDao;

    @Override
    public CaptainDTO findCaptainById(Long id) {
        return CaptainMapper.INSTANCE.toCaptainDto(captainDao.findCaptainById(id));
    }

    @Override
    public Page<CaptainDTO> findAll(Pageable pageable) {
        Page<Captain> captainPage = captainDao.findAll(pageable);
        return captainPage.map(CaptainMapper.INSTANCE::toCaptainDto);
    }

    @Override
    public Captain saveCaptain(CaptainDTO captainDTO) {
        if(captainDao.existsByPhoneNumber(captainDTO.getPhoneNumber())) {
            throw new DataIntegrityViolationException("Số điện thoại đã tồn tại!");
        }

        if(captainDao.existsByShipLicense(captainDTO.getShipLicense())) {
            throw new DataIntegrityViolationException("Số bằng lái đã tồn tại trong hệ thống!");
        }
        Ship ship = shipDao.findShipById(captainDTO.getShipId());
        Captain captain = Captain.builder()
                .ship(ship)
                .shipLicense(captainDTO.getShipLicense())
                .address(captainDTO.getAddress())
                .firstname(captainDTO.getFirstname())
                .lastname(captainDTO.getLastname())
                .phoneNumber(captainDTO.getPhoneNumber())
                .status(Status.ACTIVE)
                .createAt(LocalDate.now())
                .build();
        return captainDao.save(captain);
    }

    @Override
    public CaptainDTO updateCaptain(CaptainDTO captainDTO) {
        Captain captain=captainDao.findCaptainById(captainDTO.getId());
        if (!captain.getShipLicense().equalsIgnoreCase(captainDTO.getShipLicense())&& captainDao.existsByShipLicense(captainDTO.getShipLicense())) {
            throw new DataIntegrityViolationException("Số bằng lái tàu đã tồn tại!");
        }
        if (!captain.getPhoneNumber().equalsIgnoreCase(captainDTO.getPhoneNumber())&& captainDao.existsByPhoneNumber(captainDTO.getPhoneNumber())) {
            throw new DataIntegrityViolationException("Số điện thoại đã tồn tại!");
        }
        if (captain==null) {
            throw new DataIntegrityViolationException("Không tồn tại thuyền trưởng này!")
        ;}
            Ship ship = shipDao.findShipById(captainDTO.getShipId());
            captain.setShip(ship);
            captain.setShipLicense(captainDTO.getShipLicense());
            captain.setAddress(captainDTO.getAddress());
            captain.setFirstname(captainDTO.getFirstname());
            captain.setLastname(captainDTO.getLastname());
            captain.setPhoneNumber(captainDTO.getPhoneNumber());
            captain.setStatus(Status.valueOf(captainDTO.getStatus()));
            captain.setCreateAt(captainDTO.getCreateAt());
            captain.setUpdateAt(LocalDate.now());
            captainDao.save(captain);
            return captainDTO;
    }

    @Override
    public boolean deleteCaptain(Long id) {
        int rowResult = captainDao.softDeleteCaptain(id);
        return rowResult>0;
    }
}
