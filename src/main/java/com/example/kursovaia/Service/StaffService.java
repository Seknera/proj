package com.example.kursovaia.Service;

import com.example.kursovaia.Model.Staff;

import java.util.Optional;

public interface StaffService {
    void registerStaff(Staff staff);

    Optional<Staff> findByLogin(String login);

    void saveStaff(Staff staff);
}
