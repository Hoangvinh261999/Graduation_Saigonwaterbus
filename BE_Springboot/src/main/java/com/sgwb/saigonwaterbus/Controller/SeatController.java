package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Dao.SeatDao;
import com.sgwb.saigonwaterbus.Dao.ShipDao;
import com.sgwb.saigonwaterbus.Mapper.SeatMapper;
import com.sgwb.saigonwaterbus.Mapper.ShipMapper;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.SeatDTO;
import com.sgwb.saigonwaterbus.Model.DTO.ShipDTO;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Seat;
import com.sgwb.saigonwaterbus.Model.Ship;
import com.sgwb.saigonwaterbus.Service.SeatService;
import com.sgwb.saigonwaterbus.Service.ServiceImpl.AuthenticateService;
import com.sgwb.saigonwaterbus.Service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus")
public class SeatController {
    @Autowired
    private ShipService shipService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private SeatDao seatDao;
    @Autowired

    private ShipDao shipDao;
    @CrossOrigin(origins = "*")
    @GetMapping("/booking-ticket/{tripId}")
    public ResponseEntity<ApiResponse<List<SeatDTO>>> getSeatsForTrip(@PathVariable("tripId") Long tripId) {
        ApiResponse<List<SeatDTO>> response = new ApiResponse<>();
        if (tripId != null) {
            try {
                List<SeatDTO> seatDTOList = SeatMapper.INSTANCE.toSeatDtoList(seatService.findByShipTripId(tripId));
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Success");
                response.setResult(seatDTOList);
                return ResponseEntity.ok(response);
            } catch (NumberFormatException e) {
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Invalid tripId format");
                response.setResult(null);
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("No tripId provided");
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/booking-ticket/{tripId}/{departureDate}/getSeat")
    public ResponseEntity<ApiResponse<List<SeatDTO>>> getSeat(@PathVariable("tripId") Long tripId, @PathVariable("departureDate")LocalDate departureDate) {
        ApiResponse<List<SeatDTO>> response = new ApiResponse<>();
        if (tripId != null) {
            try {
                List<SeatDTO> seatStatusDTOList = SeatMapper.INSTANCE.toSeatDtoList(seatService.getSeat(tripId,departureDate));
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Success");
                response.setResult(seatStatusDTOList);
                return ResponseEntity.ok(response);
            } catch (NumberFormatException e) {
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Invalid tripId format");
                response.setResult(null);
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("No tripId provided");
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("admin/seats")
    public ResponseEntity<ApiResponse<Page<SeatDTO>>> getAllSeats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "73") int size) {
        ApiResponse<Page<SeatDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<SeatDTO> seatDTOPage = seatService.findAll(pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Success");
            response.setResult(seatDTOPage);
            if(seatDTOPage.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("No seats found");
                response.setResult(null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to fetch seats: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/admin/seat/save")
    public ResponseEntity<ApiResponse<Seat>> addSeat( @RequestBody SeatDTO seatDTO) {
        ApiResponse<Seat> response = new ApiResponse<>();
        try {
            Seat seat = seatService.savaSeat(seatDTO);
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("Seat created successfully");
            response.setResult(seat);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/admin/seat/update")
    public ResponseEntity<ApiResponse<Seat>> updateSeat(@RequestBody SeatDTO seatDTO) {
        ApiResponse<Seat> response = new ApiResponse<>();
        try {
            if (seatService.updateSeat(seatDTO)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Update seat success");
                response.setResult(SeatMapper.INSTANCE.toSeat(seatService.findSeatByID(seatDTO.getId())));
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Update seat failed");
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

    @DeleteMapping("/admin/seat/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteSeat(@PathVariable Long id) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            if (seatService.deleteSeat(id)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Soft delete seat success");
                response.setResult(null);
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Soft delete seat failed");
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

    @GetMapping("/admin/seat/{id}")
    public ResponseEntity<ApiResponse<SeatDTO>> getSeatById(@PathVariable Long id) {
        ApiResponse<SeatDTO> response = new ApiResponse<>();
        try {
            SeatDTO seatDTO = seatService.findSeatByID(id);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get seat success");
            response.setResult(seatDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @CrossOrigin("*")
    @GetMapping("/admin/add-seat/{shipID}")
    public ResponseEntity<ApiResponse<List<Seat>>> autoAddSeat(@PathVariable("shipID") Long shipID){
        ApiResponse<List<Seat>> response = new ApiResponse<>();
        ShipDTO shipDTO = shipService.findShipById(shipID);
        if (shipDTO == null) {
            response.setMessage("ID tàu không tồn tại");
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        String[] seats={"A","B","C","D","E","F"};
        List<Seat> listSeats= new ArrayList<>();
        for (int i=1 ; i<=12;i++){
            for (int j=0; j<seats.length;j++){
                if(i==12 && j==seats.length-1){
                    Seat seat1 = Seat.builder()
                            .ship(ShipMapper.INSTANCE.toShip(shipDTO))
                            .seatName(i+seats[j])
                            .status(Status.ACTIVE)
                            .createAt(LocalDate.now())
                            .build();
                    Seat seat2 = Seat.builder()
                            .ship(ShipMapper.INSTANCE.toShip(shipDTO))
                            .seatName(i+"G")
                            .status(Status.ACTIVE)
                            .createAt(LocalDate.now())
                            .build();
                    listSeats.add(seat1);
                    listSeats.add(seat2);
                }else {
                    Seat seat = Seat.builder()
                            .ship(ShipMapper.INSTANCE.toShip(shipDTO))
                            .seatName(i+seats[j])
                            .status(Status.ACTIVE)
                            .createAt(LocalDate.now())
                            .build();
                    listSeats.add(seat);
                }
            }
        }
        seatService.saveAll(listSeats);
        response.setCode(HttpStatus.OK.value());
        response.setResult(listSeats);
        response.setMessage("Add all seat for ship success");
        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/admin/seat/findcount/{shipid}")
    public ResponseEntity<ApiResponse<List<Seat>>> getSeatByShipId(@PathVariable("shipid") Long shipid) {
        ApiResponse<List<Seat>> response = new ApiResponse<>();
        try {
            Ship ships = shipDao.findById(shipid).get();
            List<Seat> seat = seatDao.findSeatByShip(ships);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get seat success");
            response.setResult(seat);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
