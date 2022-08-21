package pl.kwidzinski.news.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kwidzinski.news.api.DataFetcher;
import pl.kwidzinski.news.model.Article;
import pl.kwidzinski.news.model.Country;
import pl.kwidzinski.news.model.NewsCategory;
import pl.kwidzinski.news.repository.ArticleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final DataFetcher dataFetcher;
    private final ArticleRepository articleRepository;
    private final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    public Page<Article> findAllByCategory(String category, PageRequest request) {
      return articleRepository.findArticleByCategoryOrderByPublicationDateDesc(category, request);
    }

    @Scheduled(fixedDelay = 10800000)
    public void saveSportArticles() {
        saveArticlesByCategory(Country.PL.getName(), NewsCategory.GENERAL.getName());
        saveArticlesByCategory(Country.PL.getName(), NewsCategory.BUSINESS.getName());
        saveArticlesByCategory(Country.PL.getName(), NewsCategory.SPORT.getName());
        saveArticlesByCategory(Country.PL.getName(), NewsCategory.TECHNOLOGY.getName());
        saveArticlesByCategory(Country.PL.getName(), NewsCategory.SCIENCE.getName());
        saveArticlesByCategory(Country.PL.getName(), NewsCategory.HEALTH.getName());
        saveArticlesByCategory(Country.PL.getName(), NewsCategory.ENTERTAINMENT.getName());
    }

//    TODO dodać sprawdzenie czy artykuły z danego dnia sie nie duplikują
    public void saveArticlesByCategory(String country, String category) {
        final List<Article> articlesFromApi = dataFetcher.articlesFromApi(country, category);
        final List<Article> articlesByCategory = articleRepository.findArticleByCategory(category);

        if (articlesByCategory.isEmpty()) {
            articleRepository.saveAll(articlesFromApi);
        }
        final List<Article> duplicateArticles = articlesFromApi.stream()
                .flatMap(fromApi -> articlesByCategory.stream()
                        .filter(fromDb -> fromApi.getTitle().equals(fromDb.getTitle())))
                .peek(article -> article.setId(null))
                .collect(Collectors.toList());
        if (!duplicateArticles.isEmpty()) {
            articlesFromApi.removeAll(duplicateArticles);
            logger.info(String.format("Duplicates found, %d articles removed", duplicateArticles.size()));
        }
        if (!articlesFromApi.isEmpty()){
            articleRepository.saveAll(articlesFromApi);
            logger.info(String.format("Articles in %s from country: %s was saved", category, country));
        }
    }

}
