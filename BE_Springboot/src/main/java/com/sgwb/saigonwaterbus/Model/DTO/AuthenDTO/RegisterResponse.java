package com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    String firstname;
    String lastname;
    String email;
    String phoneNumber;
    String username;
    String role;
    String createAt;
}
