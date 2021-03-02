package pl.kwidzinski.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.news.api.Category;
import pl.kwidzinski.news.dao.NewsDao;

@Controller
@RequestMapping("/")
public class IndexController {

    private final NewsDao newsDao;

    public IndexController(final NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @GetMapping("/")
    public String getGeneralArticles(Model model) {
        model.addAttribute("generalArticles", newsDao.findAll(Category.GENERAL.getTableName()));
        return "general";
    }
}
