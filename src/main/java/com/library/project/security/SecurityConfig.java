package com.library.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()  // Разрешить доступ к login и статическим файлам
                        .requestMatchers("/books/add").hasAuthority("LIBRARIAN")   // Только библиотекарь может добавлять книги
                        .requestMatchers("/books/**").hasAnyAuthority("LIBRARIAN", "READER")  // Доступ к книгам для библиотекаря и читателя
                        .requestMatchers("/profile").authenticated()  // Только авторизованные пользователи могут видеть профиль
                        .anyRequest().denyAll()  // Все остальные запросы закрыты
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/profile", true)  // Переадресация после успешного входа
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")  // Переадресация после выхода
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

