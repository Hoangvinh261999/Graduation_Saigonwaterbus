package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Mapper.StationMapper;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.StationDTO;
import com.sgwb.saigonwaterbus.Model.Station;
import com.sgwb.saigonwaterbus.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/saigonwaterbus/admin")
public class StationController {
    @Autowired
    private StationService stationService;
    @GetMapping("/stations")
    public ResponseEntity<ApiResponse<Page<StationDTO>>> getAllStation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<Page<StationDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<StationDTO> stationDTOPage = stationService.findAll(pageable);
            response.setCode(1005);
            response.setMessage("Success");
            response.setResult(stationDTOPage);
            if (stationDTOPage.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("No stations found");
                response.setResult(null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Failed to fetch stations: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/station/{id}")
    public ResponseEntity<ApiResponse<StationDTO>> getStationById(@PathVariable Long id) {
        ApiResponse<StationDTO> response = new ApiResponse<>();
        try {
            StationDTO stationDTO = stationService.findStationByID(id);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Station fetched successfully");
            response.setResult(stationDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/station/save")
    public ResponseEntity<ApiResponse<Station>> addStation(@RequestBody StationDTO stationDTO) {
        ApiResponse<Station> response = new ApiResponse<>();
        try {
            Station station = stationService.saveStation(stationDTO);
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("Station created successfully");
            response.setResult(station);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/station/update")
    public ResponseEntity<ApiResponse<Station>> updateStation(@RequestBody StationDTO stationDTO) {
        ApiResponse<Station> response = new ApiResponse<>();
        try {
            if (stationService.updateStation(stationDTO)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Update station success");
                response.setResult(StationMapper.INSTANCE.toStation(stationService.findStationByID(stationDTO.getId())));
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Update station failed");
                response.setResult(null);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/station/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStation(@PathVariable Long id) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            if (stationService.deleteStation(id)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Soft delete station success");
                response.setResult(null);
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Soft delete station failed");
                response.setResult(null);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}


