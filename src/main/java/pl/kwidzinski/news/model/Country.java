package pl.kwidzinski.news.model;

public enum Country {
    PL("pl"), EN("en"), DE("de");

    private final String name;

    Country(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
