package com.example.kursovaia.Controller;

import com.example.kursovaia.Model.Book;
import org.springframework.ui.Model;
import com.example.kursovaia.Service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping("/books")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book";
    }

    @GetMapping("/books/add")
    public String showAddFilmForm(Model model) {
        model.addAttribute("film", new Book());
        return "booksAdd";
    }

    @PostMapping("/books/add")
    public String addFilm(@ModelAttribute Book book, @RequestParam("photo") MultipartFile photo) {
        try {
            bookService.save(book, photo);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/books";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
