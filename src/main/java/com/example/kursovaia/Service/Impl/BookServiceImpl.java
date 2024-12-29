package com.example.kursovaia.Service.Impl;

import com.example.kursovaia.Model.Book;
import com.example.kursovaia.Repository.BookRepository;
import com.example.kursovaia.Service.BookService;
import com.example.kursovaia.file.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private final FileStorageService fileStorageService;
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public void saveBook(com.example.kursovaia.Model.Book book) {

    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public  Book save(Book book, MultipartFile photo) throws IOException {
        if (photo != null && !photo.isEmpty()) {
            String photoUrl = fileStorageService.saveFile(photo);
            book.setCover_image(photoUrl);
        }
        return bookRepository.save(book);
    }

}
