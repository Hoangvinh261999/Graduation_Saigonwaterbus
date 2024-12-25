package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Mapper.TripMapper;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.TripCRUD;
import com.sgwb.saigonwaterbus.Model.Trip;
import com.sgwb.saigonwaterbus.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus/admin")
public class TripController {
    @Autowired
    private TripService tripService;
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
    @CrossOrigin("*")
    @GetMapping("/trips")
    public ResponseEntity<ApiResponse<Page<TripCRUD>>> getTripAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<Page<TripCRUD>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("departureDate").ascending());
            Page<TripCRUD> tripCRUDPage = tripService.findAllTrip(pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all trips success");
            response.setResult(tripCRUDPage);

            if (tripCRUDPage.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("No trips found");
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

    @CrossOrigin("*")
    @GetMapping("/trip/{id}")
    public ResponseEntity<ApiResponse<TripCRUD>> getTripById(@PathVariable("id") Long id) {
        ApiResponse<TripCRUD> response = new ApiResponse<>();
        try {
            TripCRUD tripCRUD = tripService.findTripByID(id);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get trip success");
            response.setResult(tripCRUD);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/trip/save")
    public ResponseEntity<ApiResponse<Trip>> saveTrip(@RequestBody TripCRUD tripCRUD) {
        ApiResponse<Trip> response = new ApiResponse<>();
        try {
            Trip saveTrip = tripService.saveTrip(tripCRUD);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Save trip success");
            response.setResult(saveTrip);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }
    @CrossOrigin("*")
    @PutMapping("/trip/update")
    public ResponseEntity<ApiResponse<Trip>> updateTrip(@RequestBody TripCRUD tripCRUD){
        ApiResponse<Trip> response = new ApiResponse<>();
        try {
            if (tripService.updateTrip(tripCRUD)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Update trip success");
                response.setResult(TripMapper.INSTANCE.toTrip(tripService.findTripByID(tripCRUD.getId())));
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Update trip failed");
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
    @DeleteMapping("/trip/delete/{id}")
    public ResponseEntity<ApiResponse<TripCRUD>> softDeleteTrip(@PathVariable("id") Long id) {
        ApiResponse<TripCRUD> response = new ApiResponse<>();
        try {
            if (tripService.deleteTripById(id)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Soft delete trip success");
                response.setResult(null);
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Soft delete trip failed");
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