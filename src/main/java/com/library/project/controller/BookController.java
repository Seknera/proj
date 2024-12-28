package com.library.project.controller;

import com.library.project.model.Book;
import com.library.project.model.Role;
import com.library.project.service.BookService;
import com.library.project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/catalog")
    public String catalog(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "catalog";
    }

    @PostMapping("/reserve")
    public String reserveBook(@RequestParam Long bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != Role.READER) {
            return "redirect:/login";
        }
        bookService.reserveBook(bookId);
        return "redirect:/catalog";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/catalog";
    }

    @PostMapping("/delete-book")
    public String deleteBook(@RequestParam Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/catalog";
    }
}

