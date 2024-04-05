package spring.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.blog.domain.Article;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
}
