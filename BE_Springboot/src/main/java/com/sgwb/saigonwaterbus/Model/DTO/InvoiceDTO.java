package com.sgwb.saigonwaterbus.Model.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {
    private Long id;
    private Long account_id;
    private String email;
    private String payMethod; // Tiền mặt: 0, VNPay: 1
    private Double totalAmount; // Thành tiền
    private String status; // Active: 1, inactive: 0
    private LocalDate createAt;
    private LocalDate updateAt;
    private LocalDate deleteAt;
    private BigDecimal percentage;
    private int year;
    private int month;
    private double totalAmountByMonth;
    private double totalAmountByDay;
    private LocalDateTime holdExpirationTime;
    private String emailBooking;

    public InvoiceDTO(LocalDateTime holdExpirationTime,Long account_id, String payMethod, Double totalAmount, String status, LocalDate createAt, LocalDate updateAt, LocalDate deleteAt) {
        this.account_id = account_id;
        this.payMethod = payMethod;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
        this.holdExpirationTime=holdExpirationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }
}
