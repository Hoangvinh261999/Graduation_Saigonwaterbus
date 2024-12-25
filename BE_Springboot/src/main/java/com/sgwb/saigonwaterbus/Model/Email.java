package com.sgwb.saigonwaterbus.Model;

import lombok.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Email {
    private String to;
    private String subject;
    private String body;
    private String contentForQR;
}
