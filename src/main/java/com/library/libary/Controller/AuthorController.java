package com.library.libary.Controller;

import com.library.libary.enity.Author;
import com.library.libary.enity.Category;
import com.library.libary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public String findAllAuthors(Model model){
        model.addAttribute("authors", authorService.findAllAuthor());
        return "authors";
    }

    @GetMapping ("/add-authors")
    public String addauthor(Model model, Author author){
        model.addAttribute("author",author);
        return "add-authors";
    }

    @PostMapping("/save-author")
    public  String addAuthor(Author author , BindingResult result, Model model,RedirectAttributes redirectAttributes){
        if (authorService.checkname(author.getName())) {

            redirectAttributes.addFlashAttribute("msg", author.getName() + " *already have in authors");
            return "redirect:/add-authors";
        }
        authorService.CreateAuthor(author);
        model.addAttribute("author", authorService.findAllAuthor());
        return "redirect:/authors";
    }

    @GetMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Model model){
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "update-author";
    }
    @PostMapping("/save-update-author/{id}")
    public String updateauthor(@PathVariable Long id, Model model, Author author, RedirectAttributes redirectAttributes){

        authorService.CreateAuthor(author);
        model.addAttribute("authors",authorService.findAllAuthor());
        return "redirect:/authors";

    }
    @GetMapping("/remove-author/{id}")
    public String deleteAuthor(@PathVariable Long id, Model model){
        authorService.DeleteAuthor(id);
        model.addAttribute("authors",authorService.findAllAuthor());
        return "redirect:/authors";
    }
}
