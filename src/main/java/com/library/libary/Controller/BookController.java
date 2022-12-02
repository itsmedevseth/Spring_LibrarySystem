package com.library.libary.Controller;

import com.library.libary.enity.Books;
import com.library.libary.enity.Publisher;
import com.library.libary.service.AuthorService;
import com.library.libary.service.BooksService;
import com.library.libary.service.CategoryService;
import com.library.libary.service.PublisherSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PublisherSerivce publisherSerivce;
    @Autowired
    private AuthorService authorService;



    @GetMapping("books")
     public String findAllBooks(Model model){
         List<Books> books = booksService.findAllBook();
         model.addAttribute("books", books);
         return "books";
     }
     @GetMapping("/book/{id}")
     public String findBook( @PathVariable Long id, Model model){
        Books book = booksService.findBookById(id);
         model.addAttribute("book", book);
         return "list-book";
     }
     @GetMapping("/remove-books/{id}")
     public String deleteBook(@PathVariable Long id, Model model)
     {
        booksService.DeleteBook(id);
        model.addAttribute("books",booksService.findAllBook());
        return "redirect:/books";
     }

     @GetMapping("/update-book/{id}")
     public  String updateBook(@PathVariable Long id,Model model){
        Books book = booksService.findBookById(id);
        model.addAttribute("book", book);
         model.addAttribute("categories", categoryService.findAllCategory());
         model.addAttribute("publishers", publisherSerivce.findAllPublisher());
         model.addAttribute("authors", authorService.findAllAuthor());
         return "update-book";
     }
     @PostMapping ("/save-update/{id}")
    public String updatebook(@PathVariable Long id,Books book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "update-book";
        }
        booksService.updateBook(book);
        model.addAttribute("book",booksService.findAllBook());
        return "redirect:/books";
     }

    @GetMapping("/add-book")
    public  String addBook(Model model,Books book){
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("publishers", publisherSerivce.findAllPublisher());
        model.addAttribute("authors", authorService.findAllAuthor());
        return "add-book";
    }



    @PostMapping("/save-book")
    public  String saveBook(Books book , RedirectAttributes redirectAttributes, Model model){

        if (booksService.checkname(book.getIsbn())) {
            redirectAttributes.addFlashAttribute("msg", book.getName() + " *already have in Book");
            return "redirect:/add-book";
        }
        booksService.CreateBook(book);
        model.addAttribute("books", booksService.findAllBook());
        return "redirect:/books";
    }
}
