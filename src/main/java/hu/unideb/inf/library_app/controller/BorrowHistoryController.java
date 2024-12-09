package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.BorrowEntity;
import hu.unideb.inf.library_app.data.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/borrow")
public class BorrowHistoryController {

    @GetMapping("/history")
    public String viewBorrowHistory(Model model) {
        return "borrow-history";
    }
}