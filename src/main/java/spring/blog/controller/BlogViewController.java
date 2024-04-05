package spring.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import spring.blog.domain.Article;
import spring.blog.dto.ArticleListViewResponse;
import spring.blog.dto.ArticleViewResponse;
import spring.blog.service.BlogService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> list = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", list);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article byId = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(byId));
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){
            model.addAttribute("article", new ArticleViewResponse());
        } else{
            Article byId = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(byId));
        }
        return "newArticle";
    }
}
