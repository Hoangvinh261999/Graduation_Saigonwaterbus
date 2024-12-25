package com.sgwb.saigonwaterbus.Controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.TicketDTO;
import com.sgwb.saigonwaterbus.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus/admin")
//coadmin @RequestMapping("/api/saigonwaterbus")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket/getallticketbyday")
    public ResponseEntity<ApiResponse<TicketDTO>> getAllTicketByDay() {
        ApiResponse<TicketDTO> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Lấy danh sách thành công");
        response.setResult(ticketService.getTotalTicketByDay());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ticket/countallcustomer")
    public ResponseEntity<ApiResponse<TicketDTO>> countAllCustomer() {
        ApiResponse<TicketDTO> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Lấy danh sách thành công");
        response.setResult(ticketService.countAllCustomer());
        return ResponseEntity.ok(response);
    }



}