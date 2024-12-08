package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookCatalogController {

    private final BookRepository bookRepository;

    public BookCatalogController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book-catalog")
    public String bookCatalog(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-catalog";
    }
}