package edu.hust.sis.service;

import edu.hust.sis.entity.Student;
import edu.hust.sis.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    public Student getById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + id));
    }

    public boolean codeExists(String code) {
        return studentRepository.existsByStudentCode(code);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}