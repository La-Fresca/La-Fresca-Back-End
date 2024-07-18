package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.Category;
import org.lafresca.lafrescabackend.Services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/lafresca/category")
@AllArgsConstructor
@Tag(name = "Category Controller")
public class CategoryController {
    private final CategoryService categoryService;

    // Add new category
    @PostMapping
    @Operation(
            description = "Add new category",
            summary = "Add new category ",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public String addNewCategory(@RequestBody Category category) { return categoryService.addNewCategory(category); }

    // Get all categories
    @GetMapping
    @Operation(
            description = "Get all categories",
            summary = "Retrieve all categories",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public List<Category> getCategories() { return categoryService.getCategories(); }

    // Search Category
    @GetMapping(path = "{id}")
    @Operation(
            description = "Search category by id",
            summary = "Retrieve categories by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public Optional<Category> getCategory (@PathVariable("id") String id) { return categoryService.getCategory(id); }

    // Delete Category
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete category by id",
            summary = "Delete categories by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public void deleteCategory (@PathVariable("id") String id) { categoryService.deleteCategory(id);}
}
