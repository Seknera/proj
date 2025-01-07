package com.example.kursovaia.Service.Impl;

import com.example.kursovaia.Model.Reader;
import com.example.kursovaia.Model.Staff;
import com.example.kursovaia.Repository.ReaderRepository;
import com.example.kursovaia.Repository.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ReaderRepository readerRepository;
    private final StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // Поиск пользователя
        Reader user = readerRepository.findByLogin(login).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        }

        Staff staff = staffRepository.findByLogin(login).orElse(null);
        if (staff != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(staff.getUsername())
                    .password(staff.getPassword())
                    .roles(staff.getRole().name())
                    .build();
        }

        throw new UsernameNotFoundException("User or Staff not found: " + login);
    }

    public void saveUser(Reader user) {
        readerRepository.save(user);
    }
    public void saveStaff(Staff staff) { staffRepository.save(staff);}
}

