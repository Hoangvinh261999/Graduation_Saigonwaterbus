package com.sgwb.saigonwaterbus.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sgwb.saigonwaterbus.Model.DTO.InvoiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    Page<InvoiceDTO> findAllByCreateAt(LocalDate createAt, Pageable pageable);
    void sellTicketByadmin(JsonNode jsonNodeData);
    List<InvoiceDTO> paymentMethod();
    List<InvoiceDTO> getDoanhThuTheoThang();
    InvoiceDTO getDoanhThuTheoNgay();
    List<InvoiceDTO> getListDoanhThu(LocalDate startDate, LocalDate endDate);
    void callUpdateCompleteBooked(String email);
}
