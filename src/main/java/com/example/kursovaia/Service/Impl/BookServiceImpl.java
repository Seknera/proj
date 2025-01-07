package com.example.kursovaia.Service.Impl;

import com.example.kursovaia.Model.Book;
import com.example.kursovaia.Model.Issue;
import com.example.kursovaia.Model.Reader;
import com.example.kursovaia.Repository.BookRepository;
import com.example.kursovaia.Repository.IssueRepository;
import com.example.kursovaia.Repository.ReaderRepository;
import com.example.kursovaia.Service.BookService;
import com.example.kursovaia.file.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private final FileStorageService fileStorageService;
    private ReaderRepository readerRepository;
    private IssueRepository issueRepository;

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

    @Override
    public boolean reserveBook(Long bookId, Long readerId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        if (book.getIsReserved()) {
            return false; // Книга уже забронирована
        }
        book.setIsReserved(true);
        bookRepository.save(book);

        // Создаем запись о бронировании
        Issue issue = new Issue();
        Reader reader =  readerRepository.findById(readerId).orElseThrow(() -> new RuntimeException("Читатель не найдена"));
        issue.setReader(reader);
        issue.setBook(book);
        issue.setIssueDate(new Date()); // Дата бронирования
        issue.setPlannedReturnDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000)); // Две недели
        issueRepository.save(issue);

        return true;
    }


    @Override
    public void confirmReturn(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        Issue issue = issueRepository.findByBook(Optional.ofNullable(book));
        issue.setActualReturnDate(new Date());
        book.setIsReserved(false);
        issueRepository.save(issue);
        bookRepository.save(book);
    }

}
