package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.UserEntity;
import hu.unideb.inf.library_app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        return "add-user"; // The template name to render
    }

    @PostMapping("/add-user")
    public String addUser(
            @RequestParam String name,
            @RequestParam String birthdate,
            @RequestParam String email,
            @RequestParam String phone_number
    ) {
        UserEntity newUser = new UserEntity();
        newUser.setName(name);
        newUser.setBirthdate(java.sql.Date.valueOf(birthdate));
        newUser.setEmail(email);
        newUser.setPhoneNumber(phone_number);

        userRepository.save(newUser);

        return "redirect:/user-catalog";
    }
}