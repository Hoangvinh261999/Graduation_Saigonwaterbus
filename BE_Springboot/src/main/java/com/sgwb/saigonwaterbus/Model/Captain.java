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
@Builder
@Table(name = "Captain")
public class Captain {
    // Thuyền trưởng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipId")
    private Ship ship;

    @Column(name = "shipLicense", columnDefinition = "varchar(12)", nullable = false, unique = true)
    private String shipLicense;

    @Column (name = "address", columnDefinition = "nvarchar(255)", nullable = false)
    private String address;

    @Column (name = "firstname", columnDefinition = "nvarchar(255)", nullable = false)
    private String firstname;

    @Column (name = "lastname", columnDefinition = "nvarchar(255)", nullable = false)
    private String lastname;

    @Column (name = "phoneNumber", columnDefinition = "varchar(10)", nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private Status status;

    @Column (name = "createAt", columnDefinition = "date")
    private LocalDate createAt;

    @Column (name = "updateAt", columnDefinition = "date")
    private LocalDate updateAt;

    @Column (name = "deleteAt", columnDefinition = "date")
    private LocalDate deleteAt;
}
