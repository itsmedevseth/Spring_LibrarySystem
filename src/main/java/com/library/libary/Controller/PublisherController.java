package com.library.libary.Controller;

import com.library.libary.enity.Author;
import com.library.libary.enity.Books;
import com.library.libary.enity.Category;
import com.library.libary.enity.Publisher;
import com.library.libary.service.PublisherSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PublisherController {
    @Autowired
    private PublisherSerivce publisherSerivce;
    @GetMapping("/publisher")
    public String findAllAuthors(Model model){
        model.addAttribute("publishers", publisherSerivce.findAllPublisher());
        return "publisher";
    }

    @GetMapping ("/add-publisher")
    public String addpublisher(Model model, Publisher publisher){
        model.addAttribute("publisher",publisher);
        return "add-publisher";
    }

    @PostMapping("/save-publisher")
    public  String addAuthor(Publisher publisher , BindingResult result, Model model , RedirectAttributes redirectAttributes){
        if (publisherSerivce.checkname(publisher.getName())) {

            redirectAttributes.addFlashAttribute("msg", publisher.getName() + " *already have in Category");
            return "redirect:/add-category";
        }
        publisherSerivce.CreatePublisher(publisher);
        model.addAttribute("publisher", publisherSerivce.findAllPublisher());
        return "redirect:/publisher";
    }
    @GetMapping("/remove-publisher/{id}")
    public String deletepublisher(@PathVariable Long id, Model model){
        publisherSerivce.DeletePublisher(id);
        model.addAttribute("publisher",publisherSerivce.findAllPublisher());
        return "redirect:/publisher";
    }

    @GetMapping("/update-publisher/{id}")
    public String updatepublisher(@PathVariable Long id, Model model){
        Publisher publisher = publisherSerivce.findPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "update-publisher";
    }
    @PostMapping("/save-update-publisher/{id}")
    public String updatePublisher(@PathVariable Long id, Model model,Publisher publisher, BindingResult result){

        publisherSerivce.UpdatePublisher(publisher);
        model.addAttribute("publisher",publisherSerivce.findAllPublisher());
        return "redirect:/publisher";

    }
}
