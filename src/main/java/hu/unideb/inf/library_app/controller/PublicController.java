package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/home")
    public String publicHome() {
        return "public/public-home";
    }

    @GetMapping("/book-catalog")
    public String publicBookCatalog(
            @RequestParam(name = "genre", required = false) String genre,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "isbn", required = false) String isbnParam,
            @RequestParam(name = "year", required = false) Integer year,
            Model model) {

        List<BookEntity> books;
        Integer isbn = null;

        if (isbnParam != null && !isbnParam.trim().isEmpty()) {
            try {
                isbn = Integer.parseInt(isbnParam.trim());
            } catch (NumberFormatException e) {
                model.addAttribute("error", "Invalid ISBN format. Please enter a valid number.");
                model.addAttribute("books", bookRepository.findAll());
                return "public/public-book-catalog";
            }
        }

        if ((genre == null || genre.isEmpty()) &&
                (author == null || author.isEmpty()) &&
                (title == null || title.isEmpty()) &&
                isbn == null &&
                year == null) {
            books = bookRepository.findAll();
        } else {
            books = bookRepository.findByFilters(genre, author, title, isbn, year);
        }

        model.addAttribute("books", books);
        model.addAttribute("genre", genre);
        model.addAttribute("author", author);
        model.addAttribute("title", title);
        model.addAttribute("isbn", isbnParam);
        model.addAttribute("year", year);

        return "public/public-book-catalog";
    }
}