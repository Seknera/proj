package com.example.kursovaia.Repository;

import com.example.kursovaia.Model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    Optional<Reader> findByLogin(String login);

    List<Reader> getReaderById(Long id);
}

