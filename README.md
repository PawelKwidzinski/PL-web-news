# PL-Web-News-app
https://pl-web-news.herokuapp.com/
## General
Application to display headlines from Polish websites in 7 categories such as: general, business, sports, technologies, science, health, entertainment. Data is from [remote API](https://newsapi.org/account) and storaged in remote MySQL Database. With each start of application database is dropped and new data is uploaded from the remote Api.
## Technologies
* Java 8
* Maven
* Spring Boot 2.4.0
* JDBC
* MySql (remotesql.com)
* Thymeleaf
* Bootstrap
* HTML, CSS
### Design patterns
* Model-view-controller (MVC)
* Data Access Object (DAO)
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
