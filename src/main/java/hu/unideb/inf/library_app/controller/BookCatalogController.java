package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookCatalogController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book-catalog")
    public String bookCatalog(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-catalog";
    }

    @GetMapping("/borrow-book/{id}")
    public String borrowBook(@PathVariable Long id) {
        // For now, just redirect to the book catalog
        return "redirect:/book-catalog";
    }

    @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "edit-book";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/book-catalog";
    }
}