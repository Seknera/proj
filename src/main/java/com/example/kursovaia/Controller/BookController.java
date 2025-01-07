package com.example.kursovaia.Controller;

import com.example.kursovaia.Model.Book;
import com.example.kursovaia.Model.Issue;
import com.example.kursovaia.Model.Reader;
import com.example.kursovaia.Repository.IssueRepository;
import com.example.kursovaia.Repository.ReaderRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import com.example.kursovaia.Service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private BookService bookService;
    private ReaderRepository readerRepository;
    private IssueRepository issueRepository;

    @GetMapping("/books")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
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

    @PostMapping("/reserve/{id}")
    public String reserveBook(@PathVariable Long id, Model model, Authentication auth) {

        String username = auth.getName();

        Reader reader = readerRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Читатель не найден"));

        Long readerId = reader.getId();

        boolean success = bookService.reserveBook(id, readerId);
        if (!success) {
            model.addAttribute("error", "Книга уже забронирована!");
        }
        return "redirect:/books";
    }

    @PostMapping("/confirm-return/{id}")
    public String confirmReturn(@ModelAttribute Issue issue, @PathVariable Long id, Model model) {
        try {
            bookService.confirmReturn(id);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/issues")
    public String getIssues(Model model) {
        model.addAttribute("issues", issueRepository.findAll());
        return "issues";
    }

}

