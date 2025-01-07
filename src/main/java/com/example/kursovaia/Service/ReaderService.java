package com.example.kursovaia.Service;

import com.example.kursovaia.Model.Reader;

import java.util.Optional;

public interface ReaderService {
    void registerReader(Reader reader);

    void saveUser(Reader reader);

    Optional<Reader> findByLogin(String login);
}