package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.entity.UserEntity;
import hu.unideb.inf.library_app.data.entity.BorrowEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import hu.unideb.inf.library_app.data.repository.UserRepository;
import hu.unideb.inf.library_app.data.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class BorrowController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowRepository borrowRepository;

    @GetMapping("/borrow/{bookId}")
    public String showBorrowForm(@PathVariable Long bookId, Model model) {
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + bookId));

        model.addAttribute("book", book);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("borrowDate", Date.valueOf(LocalDate.now()));
        return "borrow-book";
    }

    @GetMapping("/borrow-form")
    public String showBorrowForm(Model model) {
        model.addAttribute("borrow", new BorrowEntity());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "borrow-form";
    }

    @PostMapping("/borrow/add")
    public String addBorrow(BorrowEntity borrow) {
        borrowRepository.save(borrow);

        // Decrease the book quantity by 1
        BookEntity book = borrow.getBook();
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        return "redirect:/borrow-history";
    }

    @GetMapping("/borrow-history")
    public String showBorrowHistory(Model model) {
        model.addAttribute("borrows", borrowRepository.findAll());
        return "borrow-history";
    }
}