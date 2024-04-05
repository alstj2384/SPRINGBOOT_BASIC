package spring.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.blog.domain.Article;
import spring.blog.dto.AddArticleRequest;
import spring.blog.repository.BlogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }
}
