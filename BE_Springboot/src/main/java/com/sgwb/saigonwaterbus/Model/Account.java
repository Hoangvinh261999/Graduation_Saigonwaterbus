package com.sgwb.saigonwaterbus.Model;

import com.sgwb.saigonwaterbus.Model.Enum.Role;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table (name = "Account")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", columnDefinition = "nvarchar(255)", nullable = true)
    private String firstname;

    @Column(name = "lastname", columnDefinition = "nvarchar(255)", nullable = true)
    private String lastname;

    @Column(name = "email", columnDefinition = "nvarchar(255)", nullable = true, unique = true)
    private String email;

    @Column(name = "phoneNumber", columnDefinition = "varchar(255)", nullable = true, unique = true)
    private String phoneNumber;

    @Column(name = "username", columnDefinition = "nvarchar(255)", nullable = true)
    private String username;

    @Column(name = "password", columnDefinition = "nvarchar(255)", nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private Status status;

    @Column(name = "createAt", columnDefinition = "datetime", nullable = true)
    private LocalDate createAt;

    @Column(name = "updateAt", columnDefinition = "datetime", nullable = true)
    private LocalDate updateAt;

    @Column(name = "deleteAt", columnDefinition = "datetime", nullable = true)
    private LocalDate deleteAt;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
