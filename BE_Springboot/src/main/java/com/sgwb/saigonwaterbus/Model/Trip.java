package com.sgwb.saigonwaterbus.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sgwb.saigonwaterbus.Model.DTO.TripDTO;
import com.sgwb.saigonwaterbus.Model.Enum.Fixed;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Trip")
@SqlResultSetMapping(
        name = "TripDTOResult",
        classes = @ConstructorResult(
                targetClass = TripDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "totalSeats", type = Integer.class),
                        @ColumnResult(name = "departureDate", type = LocalDate.class),
                        @ColumnResult(name = "departureTime", type = String.class),
                        @ColumnResult(name = "arrivalTime", type = String.class),
                        @ColumnResult(name = "fromTerminal", type = String.class),
                        @ColumnResult(name = "toTerminal", type = String.class),
                        @ColumnResult(name = "startTerminal", type = String.class),
                        @ColumnResult(name = "endTerminal", type = String.class),
                        @ColumnResult(name = "availableSeats", type = Integer.class),
                        @ColumnResult(name = "addressStart", type = String.class),
                        @ColumnResult(name = "addressEnd", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "Trip.findTripByRouteAndDate",
        query = "{call findTripByRouteAndDate(:fromTerminalId, :toTerminalId, :departureDate)}",
        resultSetMapping = "TripDTOResult"
)
public class Trip {
    // Chuyến tàu
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departureDate", columnDefinition = "datetime", nullable = false)
    private LocalDate departureDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "departureTime", nullable = false)
    private String departureTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "arrivalTime", nullable = false)
    private String arrivalTime;

    @Column(name = "availableSeats", columnDefinition = "int", nullable = false)
    private Integer availableSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "fixed", nullable = false)
    private Fixed fixed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "createAt", columnDefinition = "datetime")
    private LocalDate createAt;

    @Column(name = "updateAt", columnDefinition = "datetime")
    private LocalDate updateAt;

    @Column(name = "deleteAt", columnDefinition = "datetime")
    private LocalDate deleteAt;

    @ManyToOne
    @JoinColumn(name = "shipId")
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "routeId", nullable = false)
    private Route route;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}

