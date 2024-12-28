package com.library.project.controller;

import com.library.project.model.User;
import com.library.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, HttpSession session, Model model) {
        try {
            User user = userService.authenticate(login, password);
            session.setAttribute("user", user); // Сохраняем пользователя в сессии
            return "redirect:/profile"; // Переход на страницу профиля
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid login or password");
            return "login"; // Страница с ошибкой
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }
}
