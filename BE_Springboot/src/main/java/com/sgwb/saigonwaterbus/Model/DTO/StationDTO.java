package com.sgwb.saigonwaterbus.Model.DTO;



import com.sgwb.saigonwaterbus.Model.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO {
    private Long id;
    private String address;
    private String name;
    private Status status;
    private LocalDate create_at;
    private LocalDate update_at;
    private LocalDate delete_at;
}
