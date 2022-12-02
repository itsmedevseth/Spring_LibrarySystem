package com.library.libary.Controller;

import com.library.libary.Repository.CategoryRepository;
import com.library.libary.enity.Books;
import com.library.libary.enity.Category;
import com.library.libary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String homepage(){
        return "Home";
    }
    @GetMapping("/categories")
    public String findAllCategories(Model model){
        model.addAttribute("categories", categoryService.findAllCategory());
        return "categories";
    }

    @GetMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable Long id, Model model){
        categoryService.DeleteCategory(id);
        model.addAttribute("categories",categoryService.findAllCategory());
        return "redirect:/categories";
    }
    @GetMapping("/update-category/{id}")
    public String updateCategory(@PathVariable Long id, Model model){
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category", category);
        return "update-category";
    }
    @PostMapping("/save-update-category/{id}")
    public String updatecategory(@PathVariable Long id, Model model,Category category, BindingResult result){
        if (result.hasErrors())
        {
            return "update-category";
        }
          categoryService.UpdateCategory(category);
        model.addAttribute("category",categoryService.findAllCategory());
        return "redirect:/categories";

    }
    @GetMapping ("/add-category")
    public String addcategory(Model model,Category category){
        model.addAttribute("category",category);
        return "add-category";
    }

    @PostMapping("/save-category")
    public  String addCategory(Category category , Model model,RedirectAttributes redirectAttributes ) {
        if (categoryService.checkname(category.getName())) {

            redirectAttributes.addFlashAttribute("msg", category.getName() + " *already have in Category");
            return "redirect:/add-category";
        }
            categoryService.CreateCategory(category);
            model.addAttribute("category", categoryService.findAllCategory());
            return "redirect:/categories";
    }
}
