package org.lafresca.lafrescabackend.Services;

import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.DTO.CategoryDTO;

import org.lafresca.lafrescabackend.DTO.CategoryDTOMapper;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Category;
import org.lafresca.lafrescabackend.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;
    private final SystemLogService systemLogService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryDTOMapper categoryDTOMapper, SystemLogService systemLogService) {
        this.categoryRepository = categoryRepository;
        this.categoryDTOMapper = categoryDTOMapper;
        this.systemLogService = systemLogService;
    }

    // Add New Category
    public String addNewCategory(Category category) {
        String error = null;

        if (category.getName() == null || category.getName().isEmpty()) {
            error = "Category name cannot be empty";
        }
        else if (category.getDescription() == null || category.getDescription().isEmpty()) {
            error = "Category description cannot be empty";
        }
        else if (category.getCafeId() == null || category.getCafeId().isEmpty()) {
            error = "Cafe id cannot be empty";
        }

        Category alreadyExisting = categoryRepository.findByName(category.getCafeId(), category.getName());

        if (alreadyExisting != null) {
            error = "Category already exists";
        }

        if (error == null) {
            Category newCategory = categoryRepository.save(category);

            String user = SecurityContextHolder.getContext().getAuthentication().getName();
            LocalDateTime now = LocalDateTime.now();

            String message = now + " " + user + " " + "Created new category (id: " + newCategory.getId() + ") to cafe " + newCategory.getCafeId();
            systemLogService.writeToFile(message);
            log.info(message);
        }

        return error;
    }

    // Get All Categories
    public List<CategoryDTO> getCategories(String cafeId) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get all categories" ;
        systemLogService.writeToFile(message);
        log.info(message);

        return categoryRepository.findByCafeId(cafeId)
                .stream()
                .map(categoryDTOMapper)
                .collect(Collectors.toList());
    }

    // Search Category
    public Optional<Category> getCategory(String id) {
        categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get category - " + id ;
        systemLogService.writeToFile(message);
        log.info(message);

        return categoryRepository.findById(id);
    }

    // Delete Category
    public void deleteCategory(String id) {
        categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepository.deleteById(id);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Deleted category - " + id ;
        systemLogService.writeToFile(message);
        log.info(message);
    }

    // Update Category
    public void updateCategory(String id, Category category) {
        Category existingCategory = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (category.getName() != null && !category.getName().isEmpty()) {
            existingCategory.setName(category.getName());
        }
        if (category.getDescription() != null && !category.getDescription().isEmpty()) {
            existingCategory.setDescription(category.getDescription());
        }
        if (category.getCafeId() != null && !category.getCafeId().isEmpty()) {
            existingCategory.setCafeId(category.getCafeId());
        }

        categoryRepository.save(existingCategory);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Updated category (id: " + id + ")" ;
        systemLogService.writeToFile(message);
        log.info(message);
    }
}
