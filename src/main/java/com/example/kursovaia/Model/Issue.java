package com.example.kursovaia.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "Issue")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_issue")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_reader", nullable = false, referencedColumnName = "id_reader", foreignKey = @ForeignKey(name = "fk_issue_reader"))
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "id_book", nullable = false, referencedColumnName = "id_book", foreignKey = @ForeignKey(name = "fk_issue_book"))
    private Book book;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column(name = "planned_return_date", nullable = false)
    private Date plannedReturnDate;

    @Column(name = "actual_return_date", nullable = false)
    private Date actualReturnDate;
}
