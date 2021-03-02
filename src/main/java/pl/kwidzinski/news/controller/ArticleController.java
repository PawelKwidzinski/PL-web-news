package pl.kwidzinski.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.news.api.Category;
import pl.kwidzinski.news.dao.NewsDao;

@Controller
@RequestMapping("/news")
public class ArticleController {

    private final NewsDao newsDao;

    @Autowired
    public ArticleController(final NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @GetMapping("/general")
    public String getGeneralArticles(Model model) {
        model.addAttribute("generalArticles", newsDao.findAll(Category.GENERAL.getTableName()));
        return "general";
    }

    @GetMapping("/business")
    public String getBusinessArticles(Model model) {
        model.addAttribute("businessArticles", newsDao.findAll(Category.BUSINESS.getTableName()));
        return "business";
    }

    @GetMapping("/sports")
    public String getSportArticles(Model model) {
        model.addAttribute("sportArticles", newsDao.findAll(Category.SPORT.getTableName()));
        return "sport";
    }

    @GetMapping("/technologies")
    public String getTechnologyArticles(Model model) {
        model.addAttribute("technologiesArticles", newsDao.findAll(Category.TECHNOLOGY.getTableName()));
        return "technology";
    }

    @GetMapping("/science")
    public String getScienceArticles(Model model) {
        model.addAttribute("scienceArticles", newsDao.findAll(Category.SCIENCE.getTableName()));
        return "science";
    }

    @GetMapping("/health")
    public String getHealthArticles(Model model) {
        model.addAttribute("healthArticles", newsDao.findAll(Category.HEALTH.getTableName()));
        return "health";
    }

    @GetMapping("/entertainment")
    public String getEntertainmentArticles(Model model) {
        model.addAttribute("entertainmentArticles", newsDao.findAll(Category.ENTERTAINMENT.getTableName()));
        return "entertainment";
    }
}
