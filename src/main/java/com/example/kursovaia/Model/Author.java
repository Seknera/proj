package com.example.kursovaia.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastNameAuthor;

    @Column(name = "first_name", nullable = false)
    private String firstNameAuthor;

    @Column(name = "middle_name", nullable = false)
    private String maddleNameAuthor;

    @Column(name = "nationality", nullable = false)
    private String nationalityAuthor;

    @Column(name = "birth_date", nullable = false)
    private Date birthDateAuthor;
}
