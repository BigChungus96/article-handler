package com.test.articlehandler.service;


import com.test.articlehandler.model.Article;
import com.test.articlehandler.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article){
        articleRepository.save(article);
        return article;
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
