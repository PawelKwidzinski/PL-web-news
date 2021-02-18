package pl.kwidzinski.news.dao;

import pl.kwidzinski.news.dto.ArticleDto;
import pl.kwidzinski.news.model.Article;

import java.util.List;

public interface NewsDao {
    List<ArticleDto> findAll(String tableName);
    ArticleDto getOne(Long id, String tableName);
    void update(ArticleDto articleToUpdate);
}
