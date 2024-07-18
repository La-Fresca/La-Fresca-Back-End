package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.Category;
import org.lafresca.lafrescabackend.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    // Add New Category
    public String addNewCategory(Category category) {
        String error = null;

        if (category.getName() == null || category.getName().isEmpty()) {
            error = "Category name cannot be empty";
        }
        else if (category.getDescription() == null || category.getDescription().isEmpty()) {
            error = "Category description cannot be empty";
        }

        if (error == null) {
            categoryRepository.save(category);
        }

        return error;
    }



}
