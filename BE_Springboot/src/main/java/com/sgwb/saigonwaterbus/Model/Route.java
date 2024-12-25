package com.sgwb.saigonwaterbus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "Route")
public class Route {
    // Tuyến tàu
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nameRoute", columnDefinition = "nvarchar(255)", nullable = false)
    private String nameRoute;

    @ManyToOne
    @JoinColumn(name = "from_terminal", nullable = false)
    private Station fromTerminal;

    @ManyToOne
    @JoinColumn(name = "to_terminal", nullable = false)
    private Station toTerminal;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<RouteWaypoints> waypoints;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private Status status;

    @Column (name = "createAt", columnDefinition = "datetime")
    private LocalDate createAt;

    @Column (name = "updateAt", columnDefinition = "datetime")
    private LocalDate updateAt;

    @Column (name = "deleteAt", columnDefinition = "datetime")
    private LocalDate deleteAt;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trip> trips;
}
