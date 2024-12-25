package com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    String token;
    String username;
    private boolean authenticate;
}
