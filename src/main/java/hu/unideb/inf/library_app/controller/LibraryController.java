package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hw")
    public ResponseEntity<String> helloworld() {
        return new ResponseEntity<>("Hello, world!", HttpStatus.OK);
    }

    @PostMapping("/savebook")
    public ResponseEntity<BookEntity> saveBook(@RequestBody BookEntity bookEntity) {
        try {
            BookEntity savedBook = bookRepository.save(bookEntity);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatebook")
    public ResponseEntity<BookEntity> updateBook(@RequestBody BookEntity bookEntity) {
        if (bookEntity.getId() <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (bookRepository.existsById(bookEntity.getId())) {
            BookEntity updatedBook = bookRepository.save(bookEntity);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book")
    public ResponseEntity<Void> deleteBook(@RequestParam Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/books")
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // /api/filterbook?title=catcher&author=salinger&pubYear=1951-07-16
    @GetMapping("/filterbook")
    public List<BookEntity> filterBooks(@RequestParam(required = false) String title,
                                        @RequestParam(required = false) String author,
                                        @RequestParam(required = false) String isbn,
                                        @RequestParam(required = false) String genre,
                                        @RequestParam(required = false) Integer quantity,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date pubYear) {

        List<BookEntity> filteredBooks = bookRepository.findAll()
                .stream()
                .filter(book -> title == null || book.getTitle().contains(title))
                .filter(book -> author == null || book.getAuthor().contains(author))
                .filter(book -> isbn == null || book.getIsbn().equals(isbn))
                .filter(book -> genre == null || book.getGenre().contains(genre))
                .filter(book -> quantity == null || book.getQuantity().equals(quantity))
                .filter(book -> pubYear == null || book.getPublicationYear().equals(pubYear))
                .collect(Collectors.toList());

        return filteredBooks;
    }
}