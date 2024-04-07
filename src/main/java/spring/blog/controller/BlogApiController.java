package spring.blog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.blog.domain.Article;
import spring.blog.dto.AddArticleRequest;
import spring.blog.dto.ArticleResponse;
import spring.blog.dto.UpdateArticleRequest;
import spring.blog.service.BlogService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request
                    , Principal principal){
        Article save = blogService.save(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> list = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(list);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }


    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article update = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(update);
    }

}
