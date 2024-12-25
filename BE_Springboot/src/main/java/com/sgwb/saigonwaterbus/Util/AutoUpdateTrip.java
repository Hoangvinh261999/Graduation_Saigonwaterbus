package com.sgwb.saigonwaterbus.Util;

import com.sgwb.saigonwaterbus.Dao.InvoiceDao;
import com.sgwb.saigonwaterbus.Dao.TripDao;
import com.sgwb.saigonwaterbus.Model.DTO.TripDTO;
import com.sgwb.saigonwaterbus.Model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@EnableScheduling
public class AutoUpdateTrip {
    @Autowired
    TripDao tripDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Scheduled(cron = "*/15 * * * * *")
    public void autoUpdateTrip(){
        LocalDate dateNow = LocalDate.now();
        LocalDate newEstDate= tripDao.findNewestDate();
        LocalDate dateOldest= tripDao.findOldestDate();
        if(dateOldest!=null && ChronoUnit.DAYS.between(dateOldest,dateNow)==1){
            List<Trip> listTrip= tripDao.findAllByDepartureDate(dateOldest);
            for(Trip tripUpdate : listTrip){
                tripUpdate.setDepartureDate(newEstDate.plusDays(1));
                tripUpdate.setCreateAt(dateNow);
            }
            tripDao.saveAll(listTrip);
        }
    }
    @Scheduled(cron = "*/15 * * * * *")
    public void autoRemoveHoldTicketAndInvoice(){
        invoiceDao.callDeleteExpiredInProgressInvoices();
    }
}
