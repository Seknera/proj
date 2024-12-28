package com.library.project.service;

import com.library.project.model.Book;
import com.library.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void reserveBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.isReserved()) {
            book.setReserved(true);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book is already reserved");
        }
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}