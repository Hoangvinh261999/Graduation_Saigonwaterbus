package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sgwb.saigonwaterbus.Dao.*;
import com.sgwb.saigonwaterbus.Mapper.InvoiceMapper;
import com.sgwb.saigonwaterbus.Model.DTO.InvoiceDTO;
import com.sgwb.saigonwaterbus.Model.DTO.SeatDTO;
import com.sgwb.saigonwaterbus.Model.Enum.PayMethod;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Model.Invoice;
import com.sgwb.saigonwaterbus.Model.Seat;
import com.sgwb.saigonwaterbus.Model.Ticket;
import com.sgwb.saigonwaterbus.Service.InvoiceService;
import com.sgwb.saigonwaterbus.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private TripDao tripDao;
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    MailService mailService;

    @Override
    public List<InvoiceDTO> paymentMethod() {
        List<Object[]> result = invoiceDao.getPercentagePaymentMethod();
        List<InvoiceDTO> invoiceDTOs = result.stream().map(objects -> {
            String payMethod = (String) objects[0];
            BigDecimal percentage = (BigDecimal) objects[1];
            return InvoiceDTO.builder()
                    .payMethod(payMethod)
                    .percentage(percentage)
                    .build();
        }).collect(Collectors.toList());
        return invoiceDTOs;
    }

    @Override
    public List<InvoiceDTO> getDoanhThuTheoThang() {
        List<Object[]> result = invoiceDao.getDoanhThuTheoThang();
        List<InvoiceDTO> invoiceDTOs = result.stream().map(objects -> {
            int year = (Integer) objects[0];
            int month = (Integer) objects[1];
            double totalAmountByMonth = (Double) objects[2];
            return InvoiceDTO.builder()
                    .year(year)
                    .month(month)
                    .totalAmountByMonth(totalAmountByMonth)
                    .build();
        }).collect(Collectors.toList());
        return invoiceDTOs;
    }

    @Override
    public InvoiceDTO getDoanhThuTheoNgay() {
        Object result = invoiceDao.getDoanhThuTheoNgay();
        double totalAmountByDay= (Double) result;
        return InvoiceDTO.builder()
                .totalAmountByDay(totalAmountByDay)
                .build();
    }

    @Override
    public List<InvoiceDTO> getListDoanhThu(LocalDate startDate, LocalDate endDate) {
        List<Object[]> result = invoiceDao.getListDoanhThu(startDate, endDate);
        List<InvoiceDTO> invoiceDTOS = result.stream().map(objects -> {
            Date sqlDate = (Date) objects[2];
            return InvoiceDTO.builder()
                    .totalAmount((Double) objects[0])
                    .payMethod((String) objects[1])
                    .createAt(sqlDate.toLocalDate())
                    .build();
        }).collect(Collectors.toList());
        return invoiceDTOS;
    }

    @Override
    public void callUpdateCompleteBooked(String emailBooking) {
        try {
            invoiceDao.UpdateTicketAndInvoiceComplete(emailBooking);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Page<InvoiceDTO> findAllByCreateAt(LocalDate createAt, Pageable pageable) {
        Page<Invoice> InvoiceDTOPage = invoiceDao.findAllByCreateAt(createAt, pageable);
        return InvoiceDTOPage.map(InvoiceMapper.INSTANCE::toiInvoiceDto);
    }

    @Override
    public void sellTicketByadmin(JsonNode dataSellTicket){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String name = dataSellTicket.get("name").asText();
            Long trip = Long.valueOf(dataSellTicket.get("trip").asText());
            String phone = dataSellTicket.get("phone").asText();
            String email = dataSellTicket.get("email").asText();
            String payment = dataSellTicket.get("payment").asText();

            Invoice inoInvoice= new Invoice();
            inoInvoice.setAccount(accountDao.findByEmail(email));
            inoInvoice.setStatus(Status.COMPLETED);
            inoInvoice.setPayMethod(PayMethod.valueOf(payment));
            inoInvoice.setCreateAt(LocalDate.now());
            inoInvoice.setDeleteAt(null);
            JsonNode seatsNode = dataSellTicket.get("seat");

            SeatDTO[] seatsArray = mapper.treeToValue(seatsNode, SeatDTO[].class);
            inoInvoice.setTotalAmount((double)(seatsArray.length*15000));
            invoiceDao.save(inoInvoice);

            List<SeatDTO> ls = Arrays.asList(seatsArray);
            for (SeatDTO s : ls) {
                Seat st = seatDao.findSeatById(s.getId());
                Ticket ticketDTO = new Ticket();
                ticketDTO.setCreateAt(inoInvoice.getCreateAt());
                ticketDTO.setDeleteAt(null);
                ticketDTO.setUpdateAt(null);
                ticketDTO.setInvoice(inoInvoice);
                ticketDTO.setSeat(st);
                ticketDTO.setPrice(15000.0);
                ticketDTO.setStatus(Status.BOOKED);
                ticketDTO.setTrip(tripDao.findTripById(trip));
                ticketDTO.setDepartureDate(tripDao.findTripById(trip).getDepartureDate());
                ticketDao.save(ticketDTO);
            }
            String seatNamesString = ls.stream()
                    .map(SeatDTO::getSeatName)
                    .collect(Collectors.joining(", "));
//            mailService.sendMailBuyTicket(tripDao.findTripById(trip),name,seatNamesString,email);
        }catch (JsonProcessingException exception){
            throw new RuntimeException("Lỗi dữ liệu đầu vào khi bán vé.Kiểm tra lại.");
//        } catch (MessagingException | IOException | WriterException e) {

        } catch (IOException e) {
            throw new RuntimeException("Lỗi gửi mail");
        }
    }


}
