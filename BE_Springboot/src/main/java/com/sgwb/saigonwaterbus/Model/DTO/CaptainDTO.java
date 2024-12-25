package com.sgwb.saigonwaterbus.Model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CaptainDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String address;
    private String shipLicense;
    private String status;
    private LocalDate createAt;
    private LocalDate updateAt;
    private LocalDate deleteAt;
    private Long shipId;
}
