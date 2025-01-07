package com.example.kursovaia.Controller;

import com.example.kursovaia.Model.Reader;
import com.example.kursovaia.Model.Role;
import com.example.kursovaia.Model.Staff;
import com.example.kursovaia.Service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class StaffAuthController {

    private final StaffService staffService;
    private final BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/register/staff")
    public String showReaderRegistrationForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staffRegister";
    }

    @PostMapping("/register/staff")
    public String registerStaff(@ModelAttribute Staff staff) {

        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        /*if (staff.getRole() == null) {
            staff.setRole(Role.LIBRARIAN);
        }*/

        staffService.saveStaff(staff);
        return "redirect:/login";
    }
}