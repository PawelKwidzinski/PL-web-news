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

    private NewsDao newsDao;

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
}
