package com.sgwb.saigonwaterbus.Controller;


import com.sgwb.saigonwaterbus.Dao.ShipDao;
import com.sgwb.saigonwaterbus.Mapper.ShipMapper;

import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.ShipDTO;
import com.sgwb.saigonwaterbus.Model.Ship;
import com.sgwb.saigonwaterbus.Service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus/admin")
public class ShipController {
    @Autowired
    private ShipService shipService;
    @Autowired
    private ShipDao shipDao;
    @GetMapping("/ships")
    public ResponseEntity<ApiResponse<Page<ShipDTO>>> getShips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        ApiResponse<Page<ShipDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ShipDTO> shipDTOPage = shipService.findAll(pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all ships success");
            response.setResult(shipDTOPage);
            if(shipDTOPage.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("No ships found");
                response.setResult(null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/ship/{id}")
    public ResponseEntity<ApiResponse<ShipDTO>> getShipById(@PathVariable("id") Long id) {
        ApiResponse<ShipDTO> response = new ApiResponse<>();
        try {
            ShipDTO shipDTO = shipService.findShipById(id);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get ship success");
            response.setResult(shipDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin("*")
    @GetMapping("/ship")
    public ResponseEntity<ApiResponse<List<Ship>>> getShips() {
        ApiResponse<List<Ship>> response = new ApiResponse<>();
        try {
            List<Ship> ship = shipDao.findAll();
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all ships success");
            response.setResult(ship);
            if(ship.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("No ships found");
                response.setResult(null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("/ship/save")
    public ResponseEntity<ApiResponse<Ship>> saveShip(@RequestBody ShipDTO shipDTO) {
        ApiResponse<Ship> response = new ApiResponse<>();
            Ship saveShip = shipService.saveShip(shipDTO);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Save ship success");
            response.setResult(saveShip);
            return ResponseEntity.ok(response);
    }
    @PutMapping("/ship/update")
    public ResponseEntity<ApiResponse<ShipDTO>> updateTrip(@RequestBody ShipDTO shipDTO){
        ApiResponse<ShipDTO> response = new ApiResponse<>();
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Update ship success");
                response.setResult(shipService.updateShip(shipDTO));
            return ResponseEntity.ok(response);
    }

    @DeleteMapping("/ship/delete/{id}")
    public ResponseEntity<ApiResponse<ShipDTO>> softDeleteShip(@PathVariable("id") Long id) {
        ApiResponse<ShipDTO> response = new ApiResponse<>();
        try {
            if (shipService.deleteShip(id)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Soft delete ship success");
                response.setResult(null);
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Soft delete ship failed");
                response.setResult(null);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }
}
