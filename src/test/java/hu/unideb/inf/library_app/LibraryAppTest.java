package hu.unideb.inf.library_app;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibraryAppTest {
    @Test
    void contextLoads() {
    }

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBorrowBook() {
        BookEntity book = new BookEntity(1L, "Test Book", "Author", 512231, "Romance", 12, 2003);
        bookRepository.save(book);

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        BookEntity updatedBook = bookRepository.findById(1L).get();
        assertEquals(11, updatedBook.getQuantity());
    }

    @Test
    void testReturnBook() {
        BookEntity book = new BookEntity(1L, "Test Book", "Author", 512231, "Romance", 12, 2003);
        bookRepository.save(book);

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        BookEntity updatedBook = bookRepository.findById(1L).get();
        assertEquals(11, updatedBook.getQuantity());
    }


}
