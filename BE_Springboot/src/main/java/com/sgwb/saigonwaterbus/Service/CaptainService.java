package com.sgwb.saigonwaterbus.Service;

import com.sgwb.saigonwaterbus.Model.Captain;
import com.sgwb.saigonwaterbus.Model.DTO.CaptainDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CaptainService {
    CaptainDTO findCaptainById(Long id);
    Page<CaptainDTO> findAll(Pageable pageable);
    Captain saveCaptain(CaptainDTO captainDTO);
    CaptainDTO updateCaptain(CaptainDTO captainDTO);
    boolean deleteCaptain(Long id);
}
