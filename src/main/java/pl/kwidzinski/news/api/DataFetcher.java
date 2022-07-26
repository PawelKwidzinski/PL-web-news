package pl.kwidzinski.news.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kwidzinski.news.api.model.NewsApi;
import pl.kwidzinski.news.model.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DataFetcher {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${baseUrl}")
    private String baseUrl;
    private final Logger logger = LoggerFactory.getLogger(DataFetcher.class);

    public List<Article> articlesFromApi(String country, String category) {
        UriComponents uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .buildAndExpand(country, category, apiKey);
        String url = uriComponentsBuilder.toUriString();

        try {
            RestTemplate restTemplate = new RestTemplate();
            Optional<NewsApi> optionalArticle = Optional.ofNullable(restTemplate.getForObject(url, NewsApi.class));
            return mapArticles(optionalArticle, country, category);

        } catch (RestClientException e) {
            logger.error("Error during fetching data from remote API", e);
        }
        return new ArrayList<>();
    }

    private List<Article> mapArticles(final Optional<NewsApi> newsApi, String country, String category) {
        return newsApi.map(news -> news.getArticles().stream().map(
                articleApi -> {
                    Article article = new Article();
                    article.setId(null);
                    article.setTitle(articleApi.getTitle());
                    article.setImageUrl(articleApi.getUrlToImage());
                    article.setDescription(articleApi.getDescription());
                    article.setPublicationDate(LocalDateTime.parse(articleApi.getPublishedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
                    article.setUrl(articleApi.getUrl());
                    article.setCountry(country);
                    article.setCategory(category);
                    return article;
                }).collect(Collectors.toList())).orElse(Collections.emptyList());
    }
}
