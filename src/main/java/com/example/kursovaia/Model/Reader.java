package com.example.kursovaia.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Reader")
public class Reader {
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


}