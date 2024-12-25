package com.sgwb.saigonwaterbus.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sgwb.saigonwaterbus.Dao.*;
import com.sgwb.saigonwaterbus.Model.*;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.LocalStorageDTO;
import com.sgwb.saigonwaterbus.Model.DTO.InvoiceDTO;
import com.sgwb.saigonwaterbus.Model.DTO.InvoiceSummaryDTO;
import com.sgwb.saigonwaterbus.Model.DTO.SeatDTO;
import com.sgwb.saigonwaterbus.Model.Enum.PayMethod;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Service.InvoiceService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.sgwb.saigonwaterbus.Service.AccountService;
import com.sgwb.saigonwaterbus.Service.InvoiceService;
import com.sgwb.saigonwaterbus.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/saigonwaterbus")
public class InvoiceController {
    @Autowired
    InvoiceDao invdao;
    @Autowired
    SeatDao seatDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    AccountService accountService;
    private final ObjectMapper objectMapper;
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private ShipDao shipDao;
    @Autowired
    private InvoiceService invoiceService;
    private TripService tripService;
    public InvoiceController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @PostMapping("/saveLocalStorageData")
    public ResponseEntity<String> saveLocalStorageData(@RequestBody LocalStorageDTO localStorageDTO) {
        Map<String, String> localStorageData = localStorageDTO.getLocalStorageData();
        try {
            Trip chuyenData = objectMapper.readValue(localStorageData.get("chuyenData"), Trip.class);
            SeatDTO[] seatData = objectMapper.readValue(localStorageData.get("seatData"), SeatDTO[].class);

            String total = localStorageData.get("total");
            String username = localStorageData.get("us");
            Optional<Account> idacc;
            idacc = accountDao.findByUsername(username);
            LocalDateTime dt = LocalDateTime.now();
            Invoice savedInvoice = saveInvoice(total, idacc, dt);
            saveTicket(seatData, savedInvoice,chuyenData);
            if (savedInvoice == null) {
                return ResponseEntity.status(500).body("Error saving invoice");
            }
            return ResponseEntity.ok("Data received and processed successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing data");
        }
    }

    @GetMapping("/admin/invoice/getdoanhthutheongay")
    public ResponseEntity<ApiResponse<InvoiceDTO>> getDoanhThuTheoNgay() {ApiResponse<InvoiceDTO> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Lấy danh sách thành công");
        response.setResult(invoiceService.getDoanhThuTheoNgay());
        return ResponseEntity.ok(response);}
    public Invoice saveInvoice(String total, Optional<Account> idacc, LocalDateTime dt) {
        try {
            PayMethod payMethod = PayMethod.fromKey(1);
            Status st = Status.fromKey(1);
            Invoice invoice = new Invoice();
            if(idacc.isEmpty()){
                invoice.setAccount(null);
            }else{
                Account account= accountDao.findByEmail(idacc.get().getEmail());
                invoice.setAccount(account);
            }
            invoice.setPayMethod(PayMethod.BANK_TRANSFER);
            invoice.setTotalAmount(Double.valueOf(total));
            invoice.setStatus(Status.COMPLETED);
            invoice.setDeleteAt(null);
            invoice.setCreateAt(LocalDate.from(dt));
            invoice.setUpdateAt(null);
            Invoice savedInvoice = invdao.save(invoice);
            return savedInvoice;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseEntity<String> saveTicket(SeatDTO[] seat, Invoice invoice, Trip chuyenData) {
        try {
            List<SeatDTO> ls = Arrays.asList(seat);
            for (SeatDTO s : ls) {
                Seat st = seatDao.findSeatById(s.getId());
                Ticket ticketDTO = new Ticket();
                ticketDTO.setCreateAt(invoice.getCreateAt());
                ticketDTO.setDeleteAt(null);
                ticketDTO.setUpdateAt(null);
                ticketDTO.setInvoice(invoice);
                ticketDTO.setSeat(st);
                ticketDTO.setPrice(15000.0);
                ticketDTO.setStatus(Status.BOOKED);
                ticketDTO.setTrip(chuyenData);
                ticketDTO.setDepartureDate(chuyenData.getDepartureDate());
                ticketDao.save(ticketDTO);
            }
            return ResponseEntity.ok("save ticket successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing data");
        }
    }

    @GetMapping("/admin/invoices/{createAt}")
    public ResponseEntity<ApiResponse<Page<InvoiceDTO>>> getAllInvoice(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @PathVariable("createAt") String createAt) {
        ApiResponse<Page<InvoiceDTO>> response = new ApiResponse<>();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(createAt, formatter);
            Pageable pageable = PageRequest.of(page, size);
            Page<InvoiceDTO> invoicePage = invoiceService.findAllByCreateAt(date, pageable);

            if (invoicePage.isEmpty()) {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Chưa có hóa đơn nào được đặt trong ngày này");
                response.setResult(null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Lấy danh sách hóa đơn thành công");
            response.setResult(invoicePage);
            return ResponseEntity.ok(response);
        } catch (DateTimeParseException e) {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Định dạng ngày không hợp lệ, sử dụng định dạng yyyy-MM-dd");
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Đã xảy ra lỗi khi lấy danh sách hóa đơn: " + e.getMessage());
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/admin/sell-ticket")
    public ResponseEntity<ApiResponse<String>> saveTicketByStaff(@RequestBody JsonNode dataSellTicket){
            invoiceService.sellTicketByadmin(dataSellTicket);
            ApiResponse apiResponse= new ApiResponse<>();
            apiResponse.setMessage("save ticket successfully");
            apiResponse.setCode(200);
            apiResponse.setResult(null);
            return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/admin/invoice/paymethod")
    public ResponseEntity<ApiResponse<List<InvoiceDTO>>> getPaymentMethod() {
        ApiResponse<List<InvoiceDTO>> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Lấy danh sách thành công");
        response.setResult(invoiceService.paymentMethod());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/invoice/getdoanhthu")
    public ResponseEntity<ApiResponse<List<InvoiceDTO>>> getDoanhThu() {
        ApiResponse<List<InvoiceDTO>> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Lấy danh sách thành công");
        response.setResult(invoiceService.getDoanhThuTheoThang());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/admin/invoice/getlistdoanhthu")
    public ResponseEntity<ApiResponse<List<InvoiceDTO>>> getListDoanhThu(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ApiResponse<List<InvoiceDTO>> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Lấy danh sách thành công");
        response.setResult(invoiceService.getListDoanhThu(startDate, endDate));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/invoice/export")
    public ResponseEntity<ApiResponse> exportExcel(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            HttpServletResponse response) throws IOException {

        ApiResponse apiResponse = new ApiResponse<>();
        List<InvoiceDTO> invoices = invoiceService.getListDoanhThu(startDate, endDate);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Invoices");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Amount");
        headerRow.createCell(1).setCellValue("Payment Method");
        headerRow.createCell(2).setCellValue("Creation Date");

        double totalAmount = 0;
        int rowNum = 1;
        for (InvoiceDTO invoice : invoices) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(invoice.getTotalAmount());
            row.createCell(1).setCellValue(invoice.getPayMethod());
            row.createCell(2).setCellValue(invoice.getCreateAt().toString());
            totalAmount += invoice.getTotalAmount();
        }

        Row totalRow = sheet.createRow(rowNum);
        totalRow.createCell(0).setCellValue("Total Amount");
        totalRow.createCell(1).setCellValue(totalAmount);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoices.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Export Excel success");
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/hold-ticket")
    public ResponseEntity<String> saveHoldTiket(@RequestBody LocalStorageDTO localStorageDTO) {
        Map<String, String> localStorageData = localStorageDTO.getLocalStorageData();
        try {
            Trip chuyenData = objectMapper.readValue(localStorageData.get("chuyenData"), Trip.class);
            SeatDTO[] seatData = objectMapper.readValue(localStorageData.get("seatData"), SeatDTO[].class);
            String orderDataJson = localStorageData.get("orderData");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode orderDataNode = objectMapper.readTree(orderDataJson);
            String email = orderDataNode.get("email").asText();
            String total = localStorageData.get("total");
            String username = localStorageData.get("us");
            Optional<Account> idacc;
            idacc = accountDao.findByUsername(username);

            LocalDateTime dt = LocalDateTime.now();
            Invoice savedInvoice = holdInvoice(total, idacc, dt,email);
            saveHoldTicket(seatData, savedInvoice,chuyenData);
            if (savedInvoice == null) {
                return ResponseEntity.status(500).body("Error saving invoice");
            }
            return ResponseEntity.ok(savedInvoice.getId().toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing data");
        }
    }
    public Invoice holdInvoice(String total, Optional<Account> idacc, LocalDateTime dt,String emailBooking) {
        try {
            PayMethod payMethod = PayMethod.fromKey(1);
            Status st = Status.fromKey(1);
            Invoice invoice = new Invoice();
            if(idacc.isEmpty()){
                invoice.setAccount(null);
            }else{
                Account account= accountDao.findByEmail(idacc.get().getEmail());
                invoice.setAccount(account);
            }
            invoice.setPayMethod(PayMethod.BANK_TRANSFER);
            invoice.setTotalAmount(Double.valueOf(total));
            invoice.setStatus(Status.INPROGRESS);
            invoice.setDeleteAt(null);
            invoice.setEmailBooking(emailBooking);
            invoice.setCreateAt(LocalDate.from(dt));
            invoice.setUpdateAt(null);
            invoice.setHoldExpirationTime(LocalDateTime.now().plusMinutes(12));
            Invoice savedInvoice = invdao.save(invoice);
            return savedInvoice;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResponseEntity<String> saveHoldTicket(SeatDTO[] seat, Invoice invoice, Trip chuyenData) {
        try {
            List<SeatDTO> ls = Arrays.asList(seat);
            for (SeatDTO s : ls) {
                Seat st = seatDao.findSeatById(s.getId());
                Ticket ticketDTO = new Ticket();
                ticketDTO.setCreateAt(invoice.getCreateAt());
                ticketDTO.setDeleteAt(null);
                ticketDTO.setUpdateAt(null);
                ticketDTO.setInvoice(invoice);
                ticketDTO.setSeat(st);
                ticketDTO.setPrice(15000.0);
                ticketDTO.setStatus(Status.INPROGRESS);
                ticketDTO.setTrip(chuyenData);
                ticketDTO.setDepartureDate(chuyenData.getDepartureDate());
                ticketDao.save(ticketDTO);
            }
            return ResponseEntity.ok("save ticket successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing data");
        }
    }
    @PutMapping("/save-booking-complete/{email}")
    public ResponseEntity<ApiResponse<String>> saveCompleteBooking(@PathVariable String email){
        invdao.UpdateTicketAndInvoiceComplete(email);
        ApiResponse apiResponse= new ApiResponse<>();
        apiResponse.setMessage("save ticket successfully");
        apiResponse.setCode(200);
        apiResponse.setResult(null);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/checking")
    public ResponseEntity<ApiResponse<List<InvoiceSummaryDTO>>> getAllBookingHistory(@RequestParam("email") String email, @RequestParam("invoiceid") long id) {
        ApiResponse<List<InvoiceSummaryDTO>> response = new ApiResponse<>();

            List<Object[]> invoiceSummaries = invdao.findInvoiceSummariesByEmailAndInvoiceId(email, id);
            List<InvoiceSummaryDTO> summaries = new ArrayList<>();
            for (Object[] row : invoiceSummaries) {
                InvoiceSummaryDTO summary = new InvoiceSummaryDTO(
                        (Long) row[0],   // id
                        (String) row[1], // routeName
                        (String) row[2], // seatsName
                        ((Number) row[3]).intValue(), // seatCount
                        (String) row[4], // createdAt
                        (Double) row[5], // totalAmount
                        (String) row[6], // payMethod
                        (Long) row[7],   // tripId
                        (String) row[8], // tripStartTime
                        (String) row[9], // tripEndtime
                        (String) row[10], // departureDate
                        (Long) row[11],  // fromTerminalId
                        (Long) row[12],  // toTerminalId
                        (String) row[13], // fromStationName
                        (String) row[14], // toStationName
                        (String) row[15]  // emailBooking
                );
                summaries.add(summary);
            }
        if(!summaries.isEmpty()){
            response.setCode(1005);
            response.setMessage("Success");
            response.setResult(summaries);
            return ResponseEntity.ok(response);

        }else{
            response.setCode(1001);
            response.setMessage("Failed to fetch booking history");
            return ResponseEntity.ok(response);
        }


    }
}