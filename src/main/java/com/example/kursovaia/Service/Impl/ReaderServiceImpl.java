package com.example.kursovaia.Service.Impl;

import com.example.kursovaia.Model.Reader;
import com.example.kursovaia.Repository.ReaderRepository;
import com.example.kursovaia.Service.ReaderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;
    private final PasswordEncoder passwordEncoder;

    public ReaderServiceImpl(ReaderRepository readerRepository, PasswordEncoder passwordEncoder) {
        this.readerRepository = readerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerReader(Reader reader) {
        reader.setPassword(passwordEncoder.encode(reader.getPassword()));
        readerRepository.save(reader);
    }

    @Override
    public void saveUser(Reader reader) {
        readerRepository.save(reader);
    }

    @Override
    public Optional<Reader> findByLogin(String login) {
        return readerRepository.findByLogin(login);
    }

}

