package com.example.kursovaia.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_author", nullable = false, referencedColumnName = "id_author", foreignKey = @ForeignKey(name = "fk_book_author"))
    private Author author;

    @ManyToOne
    @JoinColumn(name = "id_genre", nullable = false, referencedColumnName = "id_genre", foreignKey = @ForeignKey(name = "fk_book_genre"))
    private Genre genre;

    @Column(name = "year_of_publication")
    private Long yearOfPublication;

    @Column(name = "number_of_pages")
    private Long numberOfPages;

    @Column(name = "is_reserved", nullable = false)
    private Boolean isReserved = false;

    @Column(name = "cover_image", nullable = true)
    private String cover_image;
}
