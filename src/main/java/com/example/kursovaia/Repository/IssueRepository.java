package com.example.kursovaia.Repository;

import com.example.kursovaia.Model.Book;
import com.example.kursovaia.Model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Issue findByBook(Optional<Book> book);
}
