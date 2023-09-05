package com.test.articlehandler.controller;


import com.test.articlehandler.model.Category;
import com.test.articlehandler.service.CategoryService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Category> readCategory(@PathVariable Long id) {
        if (!categoryService.categoryExists(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.findCategory(id));
    }

    @PostMapping("/categories/new")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
        Category result = categoryService.save(category);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @Valid @RequestBody Category category) {
        if (!categoryService.categoryExists(id)) {
            return ResponseEntity.notFound().build();
        }
        category.setId(id);
        categoryService.save(category);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories/{id}/delete")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (!categoryService.categoryExists(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
