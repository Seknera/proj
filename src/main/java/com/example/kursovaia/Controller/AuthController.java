package com.example.kursovaia.Controller;

import com.example.kursovaia.Model.Reader;
import com.example.kursovaia.Model.Staff;
import com.example.kursovaia.Service.ReaderService;
import com.example.kursovaia.Service.StaffService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AuthController {
    ReaderService readerService;
    StaffService staffService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный логин или пароль");
        }
        return "login"; // Отправляем на страницу входа
    }

    @GetMapping("/login/error")
    public String showErrorPage(Model model) {
        model.addAttribute("error", "Неверный логин или пароль");
        return "error";
    }
}