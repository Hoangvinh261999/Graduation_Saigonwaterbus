package com.sgwb.saigonwaterbus.Model;

import com.sgwb.saigonwaterbus.Model.Enum.PayMethod;
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
@Table(name = "Invoice")
public class Invoice {
    // Hóa đơn
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @Enumerated(EnumType.STRING)
    @Column(name = "payMethod", nullable = false)
    private PayMethod payMethod;
    @Column(name = "totalAmount", nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private Status status;

    @Column(name = "createAt")
    private LocalDate createAt;

    @Column(name = "updateAt")
    private LocalDate updateAt;

    @Column(name = "deleteAt")
    private LocalDate deleteAt;

    @Column(name = "holdExpirationTime")
    private LocalDateTime holdExpirationTime;
    @Column(name = "emailBooking")
    private String emailBooking;
}
