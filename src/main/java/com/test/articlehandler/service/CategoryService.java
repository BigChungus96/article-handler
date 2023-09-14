package com.test.articlehandler.service;


import com.test.articlehandler.model.Category;
import com.test.articlehandler.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category){
        categoryRepository.save(category);
        return category;
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
    public Category findCategory(Long id){
        return categoryRepository.findById(id).get();
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public boolean categoryExists(Long id) {
        return categoryRepository.existsById(id);
    }
    public boolean checkIfCategoryExists(Category category){
        Category existingCategory = categoryRepository.findByName(category.getName());
        if(existingCategory!=null){
            return true;
        }else{
            return false;
        }
    }
}
