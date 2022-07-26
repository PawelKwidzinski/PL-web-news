package pl.kwidzinski.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.news.model.NewsCategory;
import pl.kwidzinski.news.service.ArticleService;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/general")
    public String getGeneralArticles(Model model) {
        model.addAttribute("generalArticles", articleService.findAllByCategory(NewsCategory.GENERAL.getName()));
        return "general";
    }

    @GetMapping("/business")
    public String getBusinessArticles(Model model) {
        model.addAttribute("businessArticles", articleService.findAllByCategory(NewsCategory.BUSINESS.getName()));
        return "business";
    }

    @GetMapping("/sports")
    public String getSportArticles(Model model) {
        model.addAttribute("sportArticles", articleService.findAllByCategory(NewsCategory.SPORT.getName()));
        return "sport";
    }

    @GetMapping("/technologies")
    public String getTechnologyArticles(Model model) {
        model.addAttribute("technologiesArticles", articleService.findAllByCategory(NewsCategory.TECHNOLOGY.getName()));
        return "technology";
    }

    @GetMapping("/science")
    public String getScienceArticles(Model model) {
        model.addAttribute("scienceArticles", articleService.findAllByCategory(NewsCategory.SCIENCE.getName()));
        return "science";
    }

    @GetMapping("/health")
    public String getHealthArticles(Model model) {
        model.addAttribute("healthArticles", articleService.findAllByCategory(NewsCategory.HEALTH.getName()));
        return "health";
    }

    @GetMapping("/entertainment")
    public String getEntertainmentArticles(Model model) {
        model.addAttribute("entertainmentArticles", articleService.findAllByCategory(NewsCategory.ENTERTAINMENT.getName()));
        return "entertainment";
    }
}
