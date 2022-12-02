package com.library.libary.service;
import com.library.libary.Repository.CategoryRepository;
import com.library.libary.enity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return category;
    }

    public void CreateCategory(Category category) {
        categoryRepository.save(category);
    }

    public void UpdateCategory(Category category) {
        categoryRepository.save(category);
    }


    public void DeleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        categoryRepository.deleteById(category.getId());
    }


    public boolean checkname(String item) {
        if (categoryRepository.existsByName(item)) {
            return true;
        }
        return false;
    }
}

