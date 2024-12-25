package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Dao.InvoiceDao;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.IntrospectRequest;
import com.sgwb.saigonwaterbus.Service.ServiceImpl.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/saigonwaterbus")
public class TicketBookingHistoryController {
    @Autowired
    private InvoiceDao invoiceService;
    @Autowired
    private AuthenticateService authenticateService;
    @CrossOrigin
    @GetMapping("/booking-history")
    public ResponseEntity<ApiResponse<List<Object[]>>> getAllBookingHistory(@RequestHeader HttpHeaders headers) {
        ApiResponse<List<Object[]>> response = new ApiResponse<>();
        try {
            String tokenString = headers.getFirst("Authorization");
            String token = tokenString.substring(7);
            IntrospectRequest tokenRequest = new IntrospectRequest(token);
            List<Object[]> histories = invoiceService.findInvoiceSummariesByAccountId(authenticateService.claimUserID(tokenRequest));
            response.setCode(1005);
            response.setMessage("Success");
            response.setResult(histories);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode(1001);
            response.setMessage("Failed to fetch booking history");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
