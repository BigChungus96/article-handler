package com.test.articlehandler.controller;


import com.test.articlehandler.model.Category;
import com.test.articlehandler.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> readAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> readCategory(@PathVariable Long id) {
        if (!categoryService.categoryExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        return ResponseEntity.ok(categoryService.findCategory(id));
    }

    @PostMapping("/categories/new")
    public ResponseEntity<?> createCategory(@RequestBody @Valid Category category) {
        if (!categoryService.checkIfCategoryExists(category)) {
            Category result = categoryService.saveCategory(category);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("category already exists");
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @Valid @RequestBody Category category) {
        if (!categoryService.categoryExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        if(categoryService.checkIfCategoryExists(category)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("category of this name already exists");
        }
        category.setId(id);
        categoryService.saveCategory(category);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories/{id}/delete")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (!categoryService.categoryExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
