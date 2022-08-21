package pl.kwidzinski.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kwidzinski.news.model.Article;
import pl.kwidzinski.news.model.NewsCategory;
import pl.kwidzinski.news.service.ArticleService;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/general")
    public String getGeneralArticles(Model model,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        final Page<Article> generalArticles = articleService.findAllByCategory(NewsCategory.GENERAL.getName(), PageRequest.of(page, size));
        model.addAttribute("generalArticles", generalArticles);
        return "general";
    }

    @GetMapping("/business")
    public String getBusinessArticles(Model model,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        model.addAttribute("businessArticles", articleService.findAllByCategory(NewsCategory.BUSINESS.getName(), PageRequest.of(page, size)));
        return "business";
    }

    @GetMapping("/sports")
    public String getSportArticles(Model model,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        model.addAttribute("sportArticles", articleService.findAllByCategory(NewsCategory.SPORT.getName(), PageRequest.of(page, size)));
        return "sport";
    }

    @GetMapping("/technologies")
    public String getTechnologyArticles(Model model,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        model.addAttribute("technologiesArticles", articleService.findAllByCategory(NewsCategory.TECHNOLOGY.getName(), PageRequest.of(page, size)));
        return "technology";
    }

    @GetMapping("/science")
    public String getScienceArticles(Model model,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        model.addAttribute("scienceArticles", articleService.findAllByCategory(NewsCategory.SCIENCE.getName(), PageRequest.of(page, size)));
        return "science";
    }

    @GetMapping("/health")
    public String getHealthArticles(Model model,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        model.addAttribute("healthArticles", articleService.findAllByCategory(NewsCategory.HEALTH.getName(), PageRequest.of(page, size)));
        return "health";
    }

    @GetMapping("/entertainment")
    public String getEntertainmentArticles(Model model,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "10") int size) {
        model.addAttribute("entertainmentArticles", articleService.findAllByCategory(NewsCategory.ENTERTAINMENT.getName(), PageRequest.of(page, size)));
        return "entertainment";
    }
}
