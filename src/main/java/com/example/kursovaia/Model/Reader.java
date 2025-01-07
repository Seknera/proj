package com.example.kursovaia.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Reader")
public class Reader  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reader", nullable = false)
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastNameR;

    @Column(name = "first_name", nullable = false)
    private String firstNameR;

    @Column(name = "middle_name", nullable = false)
    private String middleNameR;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "residence_address", nullable = false)
    private String residenceAddress;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.READER;

    // Реализация методов UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login; // Поле для авторизации
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Логика проверки
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Логика проверки
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Логика проверки
    }

    @Override
    public boolean isEnabled() {
        return true; // Логика проверки
    }
}