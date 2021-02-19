package pl.kwidzinski.news.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.news.api.Category;
import pl.kwidzinski.news.dto.ArticleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class NewsDaoImpl implements NewsDao {

    private JdbcTemplate jdbcTemplate;

    public NewsDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleDto> findAll(String tableName) {
        List<ArticleDto> articleList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE NOT (title IS NULL OR image_url IS NULL OR description " +
                "IS NULL OR url IS NULL OR publication_date IS NULL)";

        final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.forEach(element -> articleList.add(new ArticleDto(
                Long.parseLong(String.valueOf(element.get("news_id"))),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("image_url")),
                String.valueOf(element.get("description")),
                String.valueOf(element.get("url")),
                String.valueOf(element.get("publication_date"))
        )));
        return articleList;
    }

    @Override
    public ArticleDto getOne(final Long id, String tableName) {
        String sql = "SELECT * FROM " + tableName + " WHERE news_id=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<ArticleDto>() {
            @Override
            public ArticleDto mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                return new ArticleDto(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }
        }, id);
    }

    @Override
    public void update(final ArticleDto updateArticle) {
        String sql = "UPDATE news SET news.title=?, news.image_url=?, news.description=?, news.url=? WHERE news.news_id=?";
        jdbcTemplate.update(sql, updateArticle.getTitle(), updateArticle.getImageUrl(), updateArticle.getDescription(),
                updateArticle.getUrl(), updateArticle.getPublicationDate());
    }
}
