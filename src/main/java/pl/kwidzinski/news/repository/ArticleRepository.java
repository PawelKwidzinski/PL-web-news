package pl.kwidzinski.news.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kwidzinski.news.model.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticleByCategory(String category);

    Page<Article> findArticleByCategoryOrderByPublicationDateDesc(@RequestParam("category") String category, Pageable pageable);
}
