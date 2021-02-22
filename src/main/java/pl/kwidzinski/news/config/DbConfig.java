package pl.kwidzinski.news.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.kwidzinski.news.api.Category;
import pl.kwidzinski.news.api.Country;
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
        final String sqlGeneralDropTable = sqlDropTable(Category.GENERAL.getTableName());
        getJdbcTemplate().update(sqlGeneralDropTable);
        final String sqlBusinessDropTable = sqlDropTable(Category.BUSINESS.getTableName());
        getJdbcTemplate().update(sqlBusinessDropTable);
        final String sqlSportsDropTable = sqlDropTable(Category.SPORT.getTableName());
        getJdbcTemplate().update(sqlSportsDropTable);
        final String sqlTechnologyDropTable = sqlDropTable(Category.TECHNOLOGY.getTableName());
        getJdbcTemplate().update(sqlTechnologyDropTable);
        final String sqlScienceDropTable = sqlDropTable(Category.SCIENCE.getTableName());
        getJdbcTemplate().update(sqlScienceDropTable);
        final String sqlHealthDropTable = sqlDropTable(Category.HEALTH.getTableName());
        getJdbcTemplate().update(sqlHealthDropTable);
        final String sqlHealthDropEntertainment = sqlDropTable(Category.ENTERTAINMENT.getTableName());
        getJdbcTemplate().update(sqlHealthDropEntertainment);

        final String sqlCreateTableGeneral = sqlCreateTable(Category.GENERAL.getTableName());
        getJdbcTemplate().update(sqlCreateTableGeneral);
        final String sqlCreateTableBusiness = sqlCreateTable(Category.BUSINESS.getTableName());
        getJdbcTemplate().update(sqlCreateTableBusiness);
        final String sqlCreateTableSport = sqlCreateTable(Category.SPORT.getTableName());
        getJdbcTemplate().update(sqlCreateTableSport);
        final String sqlCreateTableTechnology = sqlCreateTable(Category.TECHNOLOGY.getTableName());
        getJdbcTemplate().update(sqlCreateTableTechnology);
        final String sqlCreateTableScience = sqlCreateTable(Category.SCIENCE.getTableName());
        getJdbcTemplate().update(sqlCreateTableScience);
        final String sqlCreateTableHealth = sqlCreateTable(Category.HEALTH.getTableName());
        getJdbcTemplate().update(sqlCreateTableHealth);
        final String sqlCreateTableEntertainment = sqlCreateTable(Category.ENTERTAINMENT.getTableName());
        getJdbcTemplate().update(sqlCreateTableEntertainment);

        final String sqlInsertGeneral = insertData(Category.GENERAL.getTableName());
        final String sqlInsertBusiness = insertData(Category.BUSINESS.getTableName());
        final String sqlInsertSport = insertData(Category.SPORT.getTableName());
        final String sqlInsertTechnology = insertData(Category.TECHNOLOGY.getTableName());
        final String sqlInsertScience = insertData(Category.SCIENCE.getTableName());
        final String sqlInsertHealth = insertData(Category.HEALTH.getTableName());
        final String sqlInsertEntertainment = insertData(Category.ENTERTAINMENT.getTableName());

        insertRestData(sqlInsertGeneral, Country.PL.getName(), Category.GENERAL.name().toLowerCase());
        insertRestData(sqlInsertBusiness, Country.PL.getName(), Category.BUSINESS.name().toLowerCase());
        insertRestData(sqlInsertSport, Country.PL.getName(), Category.SPORT.name().toLowerCase());
        insertRestData(sqlInsertTechnology, Country.PL.getName(), Category.TECHNOLOGY.name().toLowerCase());
        insertRestData(sqlInsertScience, Country.PL.getName(), Category.SCIENCE.name().toLowerCase());
        insertRestData(sqlInsertHealth, Country.PL.getName(), Category.HEALTH.name().toLowerCase());
        insertRestData(sqlInsertEntertainment, Country.PL.getName(), Category.ENTERTAINMENT.name().toLowerCase());
    }

    private String sqlDropTable(String tableName) {
        return "DROP TABLE IF EXISTS " + tableName;
    }

    private String sqlCreateTable(String tableName) {
        return "CREATE TABLE " + tableName +"(news_id int AUTO_INCREMENT PRIMARY KEY, title VARCHAR(200), " +
                "image_url VARCHAR(500), description VARCHAR(500), url VARCHAR(500), publication_date VARCHAR(25))";
    }

    private String insertData(String tableName) {
        return "INSERT INTO " + tableName + "(title, image_url, description, url, publication_date) VALUES (?, ? ,? ,?, ?)";
    }

    private void insertRestData(final String sqlInsert, String country, String category) {
        Optional<News> newsOptional = newsApi.getDataFromRemoteApi(country, category);
        newsOptional.ifPresent(news -> news.getArticles()
                .forEach(article -> getJdbcTemplate().update(sqlInsert, article.getTitle(), article.getUrlToImage(),
                        article.getDescription(), article.getUrl(), article.getPublishedAt())));
    }
}
