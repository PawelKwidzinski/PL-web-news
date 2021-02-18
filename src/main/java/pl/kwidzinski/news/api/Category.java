package pl.kwidzinski.news.api;

public enum Category {
    BUSINESS ("business_news"),
    ENTERTAINMENT("entertainment_news"),
    GENERAL("general_news"),
    HEALTH ("health_news"),
    SCIENCE ("science_news"),
    SPORT ("sport_news"),
    TECHNOLOGY("technology_news");

    private final String name;

    public String getTableName() {
        return name;
    }

    Category(final String name) {
        this.name = name;


    }
}
