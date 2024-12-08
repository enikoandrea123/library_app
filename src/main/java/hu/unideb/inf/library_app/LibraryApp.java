package hu.unideb.inf.library_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class LibraryApp {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApp.class, args);
    }
}