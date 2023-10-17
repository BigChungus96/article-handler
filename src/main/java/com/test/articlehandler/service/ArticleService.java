package com.test.articlehandler.service;


import com.test.articlehandler.model.Article;
import com.test.articlehandler.model.Category;
import com.test.articlehandler.repository.ArticleRepository;
import com.test.articlehandler.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;


    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository=categoryRepository;
    }

    public Article saveArticle(Article article) {
        List<Category> categories = article.getCategories();
        if (categories != null && !categories.isEmpty()) {
            List<Category> savedCategories = new ArrayList<>();
            for (Category category : categories) {
                Category existingCategory = categoryRepository.findByName(category.getName());
                if (existingCategory == null) {
                    savedCategories.add(categoryRepository.save(category));
                } else {
                    savedCategories.add(existingCategory);
                }
            }
            article.setCategories(savedCategories);
        }

        return articleRepository.save(article); //test
    }
    public List<Article> findAllArticles(){
        return articleRepository.findAll();
    }
    public Article findArticle(Long id){
        return articleRepository.findById(id).get();
    }
    public void deleteArticle(Long id){
        articleRepository.deleteById(id);
    }
    public boolean articleExists(Long id){
        return articleRepository.existsById(id);
    }
}
