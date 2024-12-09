package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.UserEntity;
import hu.unideb.inf.library_app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(
            @RequestParam String name,
            @RequestParam String birthdate,
            @RequestParam String email,
            @RequestParam String phone_number,
            Model model) {

        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email is already taken!");
            return "add-user";
        }

        try {
            LocalDate localBirthdate = LocalDate.parse(birthdate);
            UserEntity newUser = new UserEntity();
            newUser.setName(name);
            newUser.setBirthdate(Date.valueOf(localBirthdate));
            newUser.setEmail(email);
            newUser.setPhoneNumber(phone_number);

            userRepository.save(newUser);

            return "redirect:/user-catalog";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid date format. Please use YYYY-MM-DD.");
            return "add-user";
        }
    }
}