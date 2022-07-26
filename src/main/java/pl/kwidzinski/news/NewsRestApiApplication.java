package pl.kwidzinski.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsRestApiApplication.class, args);
    }

}
