package com.sgwb.saigonwaterbus.Model;

import com.sgwb.saigonwaterbus.Model.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ticket")
public class Ticket {
    // Vé
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tripID", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "seatID")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "invoiceID")
    private Invoice invoice;

    @Column(name = "departureDate")
    private LocalDate departureDate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private Status status;

    @Column(name = "createAt")
    private LocalDate createAt;

    @Column(name = "updateAt")
    private LocalDate updateAt;

    @Column(name = "deleteAt")
    private LocalDate deleteAt;

}
