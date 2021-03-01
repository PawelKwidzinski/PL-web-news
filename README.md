# News-app
https://pl-web-news.herokuapp.com/
## General
Application to shows headlines from polish websites. Data is from [remote API](https://newsapi.org/account) and storage in remote MySQL Database. News is updated automatically every 1 hour.
## Technologies
* Java 8
* Maven
* Spring Boot 2.4.0
* JDBC
* MySql (remotesql.com)
* Thymeleaf
* Bootstrap
* HTML, CSS
## Enpoints
```
https://pl-web-news.herokuapp.com/
https://pl-web-news.herokuapp.com/news/general
https://pl-web-news.herokuapp.com/news/business
https://pl-web-news.herokuapp.com/news/sports
https://pl-web-news.herokuapp.com/news/technologies
https://pl-web-news.herokuapp.com/news/science
https://pl-web-news.herokuapp.com/news/health
https://pl-web-news.herokuapp.com/news/entertainment
```
## Configuration
application.properties file:
```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#API
apiKey=
```
