package pl.kwidzinski.news.dao;

import pl.kwidzinski.news.dto.ArticleDto;
import pl.kwidzinski.news.model.Article;

import java.util.List;

public interface NewsDao {
    List<ArticleDto> findAll();
    ArticleDto getOne(Long id);
    void update(ArticleDto articleToUpdate);
}
