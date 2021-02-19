package pl.kwidzinski.news.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ArticleDto {

    private Long id;
    @NotBlank(message = "Title cannot be blank.")
    @NotNull(message = "Title cannot be null.")
    private String title;
    @NotBlank(message = "Image url cannot be blank.")
    @NotNull(message = "Image url cannot be null.")
    private String imageUrl;
    @NotBlank(message = "Description cannot be blank.")
    @NotNull(message = "Description cannot be null.")
    private String description;
    @NotBlank(message = "URL cannot be blank.")
    @NotNull(message = "URL cannot be null.")
    private String url;
    @NotBlank(message = "Publication date cannot be blank.")
    @NotNull(message = "Publication date be null.")
    private String publicationDate;

    public ArticleDto(final Long id, final String title, final String imageUrl, final String description,
                      final String url, final String publicationDate) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.url = url;
        this.publicationDate = publicationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    void setPublicationDate(final String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
