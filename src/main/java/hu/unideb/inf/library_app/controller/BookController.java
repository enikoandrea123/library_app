package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam Integer isbn,
            @RequestParam int publication_year,
            @RequestParam int quantity,
            @RequestParam String genre

    ) {
        BookEntity newBook = new BookEntity();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setIsbn(isbn);
        newBook.setPublicationYear(publication_year);
        newBook.setQuantity(quantity);
        newBook.setGenre(genre);

        bookRepository.save(newBook);

        return "redirect:/book-catalog";
    }
}