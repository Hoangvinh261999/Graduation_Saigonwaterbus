package com.sgwb.saigonwaterbus.Model.DTO;

public class InvoiceSummaryDTO {

    private Long id;
    private String routeName;
    private String seatsName;
    private int seatCount;
    private String createdAt;
    private Double totalAmount;
    private String payMethod;
    private Long tripId;
    private String tripStartTime;
    private String tripEndtime;
    private String departureDate; // Thêm trường này
    private Long fromTerminalId;
    private Long toTerminalId;
    private String fromStationName;
    private String toStationName;
    private String emailBooking;

    public InvoiceSummaryDTO(Long id, String routeName, String seatsName, int seatCount, String createdAt, Double totalAmount, String payMethod, Long tripId, String tripStartTime, String tripEndtime, String departureDate, Long fromTerminalId, Long toTerminalId, String fromStationName, String toStationName, String emailBooking) {
        this.id = id;
        this.routeName = routeName;
        this.seatsName = seatsName;
        this.seatCount = seatCount;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.payMethod = payMethod;
        this.tripId = tripId;
        this.tripStartTime = tripStartTime;
        this.tripEndtime = tripEndtime;
        this.departureDate = departureDate; // Khởi tạo trường này
        this.fromTerminalId = fromTerminalId;
        this.toTerminalId = toTerminalId;
        this.fromStationName = fromStationName;
        this.toStationName = toStationName;
        this.emailBooking = emailBooking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getSeatsName() {
        return seatsName;
    }

    public void setSeatsName(String seatsName) {
        this.seatsName = seatsName;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(String tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public String getTripEndtime() {
        return tripEndtime;
    }

    public void setTripEndtime(String tripEndtime) {
        this.tripEndtime = tripEndtime;
    }

    public Long getFromTerminalId() {
        return fromTerminalId;
    }

    public void setFromTerminalId(Long fromTerminalId) {
        this.fromTerminalId = fromTerminalId;
    }

    public Long getToTerminalId() {
        return toTerminalId;
    }

    public void setToTerminalId(Long toTerminalId) {
        this.toTerminalId = toTerminalId;
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    public String getEmailBooking() {
        return emailBooking;
    }

    public void setEmailBooking(String emailBooking) {
        this.emailBooking = emailBooking;
    }
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
