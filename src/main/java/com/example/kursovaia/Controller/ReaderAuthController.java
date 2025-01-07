package com.example.kursovaia.Controller;

import com.example.kursovaia.Model.Reader;
import com.example.kursovaia.Model.Role;
import com.example.kursovaia.Service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ReaderAuthController {

    private final ReaderService readerService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register/reader")
    public String showReaderRegistrationForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "readerRegister";
    }

    @PostMapping("/register/reader")
    public String registerReader(@ModelAttribute Reader reader) {
        reader.setPassword(passwordEncoder.encode(reader.getPassword()));
        if (reader.getRole() == null) {
            reader.setRole(Role.READER); // Назначаем роль READER по умолчанию
        }
        readerService.saveUser(reader);
        return "redirect:/login";
    }
}
