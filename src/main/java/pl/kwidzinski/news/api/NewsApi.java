package pl.kwidzinski.news.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kwidzinski.news.model.Article;
import pl.kwidzinski.news.model.News;

import java.util.Optional;

@RestController
public class NewsApi {
    @Value("${apiKey}")
    private String apiKey;
    Logger logger = LoggerFactory.getLogger(NewsApi.class);


    public Optional<News> getDataFromRemoteApi(String country, String category) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            UriComponents url = UriComponentsBuilder
                    .fromHttpUrl("http://newsapi.org/v2/top-headlines?country={country}&category={category}&apiKey={key}")
                    .buildAndExpand(country, category, apiKey);

            Optional<News> articleNews = Optional.ofNullable(restTemplate.getForObject(url.toUriString(), News.class));
            if (articleNews.isEmpty()) {
                logger.error("No data \"News\" from remote API");
            }
            return articleNews;
        } catch (RestClientException e) {
            logger.error("Error during fetching from remote API", e);
        }
        return Optional.empty();
    }
}
