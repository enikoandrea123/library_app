package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.UserEntity;
import hu.unideb.inf.library_app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Controller
public class UserCatalogController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user-catalog")
    public String userCatalog(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "birthdate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate,
            Model model) {

        List<UserEntity> users;

        if ((name == null || name.isEmpty()) &&
                (email == null || email.isEmpty()) &&
                (phoneNumber == null || phoneNumber.isEmpty()) &&
                (birthdate == null)) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByFilters(name, email, phoneNumber, birthdate);
        }

        model.addAttribute("users", users);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("birthdate", birthdate);

        return "user-catalog";
    }

    @GetMapping("/edit-user/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/edit-user/{id}")
    public String updateUser(@PathVariable Long id, UserEntity updatedUser) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());

        if (updatedUser.getBirthdate() != null) {
            user.setBirthdate(updatedUser.getBirthdate());
        } else {
        }

        userRepository.save(user);
        return "redirect:/user-catalog";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/user-catalog";
    }
}