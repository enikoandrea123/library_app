package hu.unideb.inf.library_app.controller;

import hu.unideb.inf.library_app.data.entity.UserEntity;
import hu.unideb.inf.library_app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserCatalogController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user-catalog")
    public String userCatalog(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-catalog";
    }

    @GetMapping("/edit-user/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/edit-user/{id}")
    public String updateUser(@PathVariable Long id, UserEntity updatedUser) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setBirthdate(updatedUser.getBirthdate());
        userRepository.save(user);
        return "redirect:/user-catalog";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/user-catalog";
    }
}