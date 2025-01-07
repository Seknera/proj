package com.example.kursovaia.Service;

import com.example.kursovaia.Model.Book;
import com.example.kursovaia.Model.Issue;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    void saveBook(Book book);
    void deleteBook(Long id);
    Book save(Book book, MultipartFile photo) throws IOException;
    boolean reserveBook(Long bookId, Long readerId);

    void confirmReturn(Long bookId);
}
