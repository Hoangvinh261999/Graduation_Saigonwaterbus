package com.sgwb.saigonwaterbus.Model;

import com.fasterxml.jackson.annotation.*;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Ship")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ship {
    // Tàu
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trip> trips;

    @Column(name = "totalSeats", columnDefinition = "int", nullable = false)
    private Integer totalSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "createAt", columnDefinition = "datetime")
    private LocalDate createAt;

    @Column(name = "updateAt", columnDefinition = "datetime")
    private LocalDate updateAt;

    @Column(name = "deleteAt", columnDefinition = "datetime")
    private LocalDate deleteAt;

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Captain> captains;

    @Column(name = "numberPlate", columnDefinition = "nvarchar(255)")
    private String numberPlate;


    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Seat> seats;
}
