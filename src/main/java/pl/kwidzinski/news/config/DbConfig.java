package pl.kwidzinski.news.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.kwidzinski.news.api.NewsApi;
import pl.kwidzinski.news.model.News;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class DbConfig {

    private final DataSource dataSource;
    private final NewsApi newsApi;

    public DbConfig(final DataSource dataSource, final NewsApi newsApi) {
        this.dataSource = dataSource;
        this.newsApi = newsApi;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDB() {
        final String sqlDropTable = "DROP TABLE IF EXISTS news";
        getJdbcTemplate().update(sqlDropTable);

        final String sqlCreateTable = "CREATE TABLE news(news_id int AUTO_INCREMENT PRIMARY KEY, title VARCHAR(1000), " +
                "image_url VARCHAR(1000), description VARCHAR(1000), url VARCHAR(1000))";
        getJdbcTemplate().update(sqlCreateTable);

        final String sqlInsert = "INSERT INTO news (title, image_url, description, url) VALUES (?, ? ,? ,?)";

        Optional<News> newsOptional = newsApi.getDataFromRemoteApi();
        newsOptional.ifPresent(news -> news.getArticles()
            .forEach(article -> getJdbcTemplate().update(sqlInsert, article.getTitle(), article.getUrlToImage(),
                    article.getDescription(), article.getUrl())));
    }
}
