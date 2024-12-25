package com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    private String token;
    private String oldPassword;
    private String newPassword;
}
