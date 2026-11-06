package edu.hust.sis.controller;

import edu.hust.sis.dto.ScoreForm;
import edu.hust.sis.entity.StudentScore;
import edu.hust.sis.service.ScoreService;
import edu.hust.sis.service.StudentService;
import edu.hust.sis.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    public ScoreController(ScoreService scoreService, StudentService studentService,
                           SubjectService subjectService) {
        this.scoreService = scoreService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("scoreForm", new ScoreForm());
        loadOptions(model);
        return "score-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("scoreForm") ScoreForm form,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            loadOptions(model);
            return "score-form";
        }
        StudentScore score = new StudentScore();
        score.setStudent(studentService.getById(form.getStudentId()));
        score.setSubject(subjectService.getById(form.getSubjectId()));
        score.setScore1(form.getScore1());
        score.setScore2(form.getScore2());
        scoreService.save(score);
        return "redirect:/";
    }

    private void loadOptions(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
    }
}