package com.example.kursovaia.Service.Impl;

import com.example.kursovaia.Model.Staff;
import com.example.kursovaia.Repository.StaffRepository;
import com.example.kursovaia.Service.StaffService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    public StaffServiceImpl(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerStaff(Staff staff) {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staffRepository.save(staff);
    }


    @Override
    public Optional<Staff> findByLogin(String login) {
        return staffRepository.findByLogin(login);
    }

    @Override
    public void saveStaff(Staff staff) {
        staffRepository.save(staff);
    }
}

