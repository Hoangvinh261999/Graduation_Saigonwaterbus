package com.sgwb.saigonwaterbus.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.sgwb.saigonwaterbus.Mapper.SeatMapper;
import com.sgwb.saigonwaterbus.Model.DTO.*;
import com.sgwb.saigonwaterbus.Service.SeatService;
import com.sgwb.saigonwaterbus.Service.StationService;
import com.sgwb.saigonwaterbus.Service.TicketService;
import com.sgwb.saigonwaterbus.Service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus")
public class TripForUser {
    private static final Logger logger = LoggerFactory.getLogger(TripForUser.class);
    @Autowired
    private TripService tripService;
    @Autowired
    private SeatService seatService;
    @CrossOrigin(origins = "*")
    @GetMapping("/booking-ticket")
    public ResponseEntity<ApiResponse<List<TripDTO>>> findTrips(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String departDate
    ) {

        ApiResponse<List<TripDTO>> response = new ApiResponse<>();
        try {
            if (from == null || to == null || departDate == null) {
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Invalid value format");
                response.setResult(null);
                return ResponseEntity.badRequest().body(response);
            }
            Long fromTerminal = Long.parseLong(from);
            Long toTerminal = Long.parseLong(to);
            LocalDate dateTime = LocalDate.parse(departDate);
            List<TripDTO> trips = tripService.findTrips(fromTerminal, toTerminal, dateTime);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Success");
            response.setResult(trips);
            if (trips.isEmpty()) {
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("No trips found");
                response.setResult(null);
                return ResponseEntity.badRequest().body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error finding trips", e);
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal server error: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }
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
    @GetMapping("/trips/{departureDate}")
    public ResponseEntity<ApiResponse<Page<TripCRUD>>> getTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @PathVariable("departureDate") String departureDate) {
        ApiResponse<Page<TripCRUD>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<TripCRUD> tripCRUDPage;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(departureDate, formatter);
            tripCRUDPage = tripService.findAllByDepartureDate(date, pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all trips success");
            response.setResult(tripCRUDPage);
            if (tripCRUDPage.isEmpty()) {
                response.setCode(401);
                response.setMessage("No trips found");
                response.setResult(tripCRUDPage);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }
    @Autowired
    TicketService ticketService;
    @GetMapping("/ticketAlls")
    public ResponseEntity<ApiResponse<Page<TicketDTO>>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<Page<TicketDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<TicketDTO> ticketdtolist = ticketService.getAllTickets(pageable);
            response.setCode(1005);
            response.setMessage("Success");
            response.setResult(ticketdtolist);
            if (ticketdtolist.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("No ticket found");
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
    @PostMapping("/check-ticket")
    public ResponseEntity<ApiResponse<String>> checkTicket(@RequestBody JsonNode jsonNode ,
                                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate
    ) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        for (JsonNode node : jsonNode) {
            if (ticketService.existsByIdAndStatus(node.get("id").asLong(),departureDate)) {
                apiResponse.setMessage("Danh sách ghế đã thay đổi");
                apiResponse.setCode(500);
                apiResponse.setResult(null);
                return ResponseEntity.ok(apiResponse);
            }
        }
        apiResponse.setMessage("Pass");
        apiResponse.setCode(200);
        apiResponse.setResult(null);
        return ResponseEntity.ok(apiResponse);
    }
}
