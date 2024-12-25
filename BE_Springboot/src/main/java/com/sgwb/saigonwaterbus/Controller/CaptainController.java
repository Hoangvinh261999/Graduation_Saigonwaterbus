package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Mapper.CaptainMapper;
import com.sgwb.saigonwaterbus.Model.Captain;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.CaptainDTO;
import com.sgwb.saigonwaterbus.Service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/saigonwaterbus/admin")
public class CaptainController {
    @Autowired
    CaptainService captainService;
    @GetMapping("/captains")
    public ResponseEntity<ApiResponse<Page<CaptainDTO>>> getAllCaptains(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        ApiResponse<Page<CaptainDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<CaptainDTO> captainDTOPage = captainService.findAll(pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Success");
            response.setResult(captainDTOPage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to fetch captains: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/captain/{id}")
    public ResponseEntity<ApiResponse<CaptainDTO>> getCaptainById(@PathVariable Long id) {
        ApiResponse<CaptainDTO> response = new ApiResponse<>();
        try {
            CaptainDTO captainDTO = captainService.findCaptainById(id);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Success");
            response.setResult(captainDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/captain/save")
    public ResponseEntity<ApiResponse<Captain>> addCaptain(@RequestBody CaptainDTO captainDTO) {
        ApiResponse<Captain> response = new ApiResponse<>();
            Captain savedCaptain = captainService.saveCaptain(captainDTO);
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("Captain created successfully");
            response.setResult(savedCaptain);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/captain/update")
    public ResponseEntity<ApiResponse<Captain>> updateCaptain(@RequestBody CaptainDTO captainDTO) {
        ApiResponse<Captain> response = new ApiResponse<>();
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Captain updated successfully");
                response.setResult(CaptainMapper.INSTANCE.toCaptain(captainService.updateCaptain(captainDTO)));
                response.setResult(null);
            return ResponseEntity.ok(response);

    }

    @DeleteMapping("/captain/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCaptain(@PathVariable Long id) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            if (captainService.deleteCaptain(id)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Captain deleted successfully");
                response.setResult(null);
            }
            else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("delete Captain failed");
                response.setResult(null);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
