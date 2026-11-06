package edu.hust.sis.controller;

import edu.hust.sis.service.ScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ScoreService scoreService;

    public HomeController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("scores", scoreService.findAllViews());
        return "index";
    }
}