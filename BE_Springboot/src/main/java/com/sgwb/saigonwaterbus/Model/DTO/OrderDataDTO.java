package com.sgwb.saigonwaterbus.Model.DTO;

import java.util.List;

public class OrderDataDTO {
    private String name;
    private String email;
    private String message;
    private String phone;
    private TripDTO trip;
    private List<SeatDTO> seat;
    private String total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public List<SeatDTO> getSeat() {
        return seat;
    }

    public void setSeat(List<SeatDTO> seat) {
        this.seat = seat;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
