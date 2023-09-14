package com.test.articlehandler.controller;


import com.test.articlehandler.model.Article;
import com.test.articlehandler.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> readAllArticles() {
        return ResponseEntity.ok(articleService.findAllArticles());
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<?> readArticle(@PathVariable Long id) {
        if (!articleService.articleExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
        return ResponseEntity.ok(articleService.findArticle(id));
    }

    @PostMapping("/articles/new")
    public ResponseEntity<Article> createArticle(@RequestBody @Valid Article article) {
        Article result = articleService.saveArticle(article);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @Valid @RequestBody Article article) {
        if (!articleService.articleExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
        article.setId(id);
        articleService.saveArticle(article);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/articles/{id}/delete")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        if (!articleService.articleExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
        articleService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }
}
