package com.sgwb.saigonwaterbus.Model;

import com.sgwb.saigonwaterbus.Model.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name", columnDefinition = "nvarchar(255)", nullable = false)
    private String name;

    @Column (name = "addressStation", columnDefinition = "nvarchar(255)", nullable = false)
    private String addressStation;

    @Enumerated(EnumType.STRING)
    @Column (name = "status")
    private Status status;

    @Column (name = "createAt", columnDefinition = "datetime")
    private LocalDate createAt;

    @Column (name = "updateAt", columnDefinition = "datetime")
    private LocalDate updateAt;

    @Column (name = "deleteAt", columnDefinition = "datetime")
    private LocalDate deleteAt;
}
