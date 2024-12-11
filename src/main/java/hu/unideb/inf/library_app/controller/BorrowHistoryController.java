package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.entity.BorrowEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import hu.unideb.inf.library_app.data.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowHistoryController {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/history")
    public String viewBorrowHistory(
            @RequestParam(name = "userName", required = false) String userName,
            @RequestParam(name = "bookTitle", required = false) String bookTitle,
            @RequestParam(name = "isbn", required = false) Integer isbn,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "borrowDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date borrowDate,
            Model model) {

        List<BorrowEntity> borrows;

        if ((userName == null || userName.isEmpty()) &&
                (bookTitle == null || bookTitle.isEmpty()) &&
                (isbn == null) &&
                (author == null || author.isEmpty()) &&
                (borrowDate == null)) {
            borrows = borrowRepository.findAll();
        } else {
            borrows = borrowRepository.findByFilters(userName, bookTitle, isbn, author, borrowDate);
        }

        model.addAttribute("borrows", borrows);
        model.addAttribute("userName", userName);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("isbn", isbn);
        model.addAttribute("author", author);
        model.addAttribute("borrowDate", borrowDate);

        return "borrow-history";
    }


}