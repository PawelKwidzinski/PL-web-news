package pl.kwidzinski.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.news.model.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticleByCategory(String category);
    List<Article> findArticleByCategoryOrderByPublicationDateDesc(String category);
}
