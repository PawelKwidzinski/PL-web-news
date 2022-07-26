package pl.kwidzinski.news.model;

public enum NewsCategory {
    BUSINESS ("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH ("health"),
    SCIENCE ("science"),
    SPORT ("sport"),
    TECHNOLOGY("technology");

    private final String name;

    public String getName() {
        return name;
    }

    NewsCategory(final String name) {
        this.name = name;


    }
}
