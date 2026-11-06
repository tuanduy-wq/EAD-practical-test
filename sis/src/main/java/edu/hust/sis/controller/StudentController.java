package edu.hust.sis.controller;

import edu.hust.sis.entity.Student;
import edu.hust.sis.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        if (!result.hasFieldErrors("studentCode")
                && studentService.codeExists(student.getStudentCode().trim())) {
            result.rejectValue("studentCode", "duplicate", "This student code already exists");
        }
        if (result.hasErrors()) {
            return "student-form";
        }
        student.setId(null); // always insert a new row
        student.setStudentCode(student.getStudentCode().trim());
        student.setFullName(student.getFullName().trim());
        studentService.save(student);
        return "redirect:/";
    }
}