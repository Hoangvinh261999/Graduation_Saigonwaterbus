package com.sgwb.saigonwaterbus.Model;

import com.sgwb.saigonwaterbus.Model.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "Seat")
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipId", nullable = false)
    private Ship ship;

    @Column (name = "seatName", columnDefinition = "nvarchar(255)", nullable = false)
    private String seatName;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private Status status;

    @Column (name = "createAt", columnDefinition = "datetime")
    private LocalDate createAt;

    @Column (name = "updateAt", columnDefinition = "datetime")
    private LocalDate updateAt;

    @Column (name = "deleteAt", columnDefinition = "datetime")
    private LocalDate deleteAt;

}
