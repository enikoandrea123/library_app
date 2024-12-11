package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import hu.unideb.inf.library_app.data.entity.BorrowEntity;
import hu.unideb.inf.library_app.data.repository.BookRepository;
import hu.unideb.inf.library_app.data.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/borrow") // Keep '/borrow' base path
public class BorrowHistoryController {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/history") // This will map '/borrow/history'
    public String viewBorrowHistory(Model model) {
        model.addAttribute("borrows", borrowRepository.findAll());
        return "borrow-history";
    }


}