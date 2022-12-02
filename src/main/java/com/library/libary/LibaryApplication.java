package com.library.libary;

import com.library.libary.enity.Author;
import com.library.libary.enity.Books;
import com.library.libary.enity.Category;
import com.library.libary.enity.Publisher;
import com.library.libary.service.BooksService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.awt.print.Book;

@Configuration
@EnableWebMvc
@SpringBootApplication
public class LibaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibaryApplication.class, args);
    }

}
