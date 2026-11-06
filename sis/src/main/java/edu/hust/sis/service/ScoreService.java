package edu.hust.sis.service;

import edu.hust.sis.dto.StudentScoreView;
import edu.hust.sis.entity.StudentScore;
import edu.hust.sis.repository.StudentScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ScoreService {

    private static final BigDecimal W1 = new BigDecimal("0.3");
    private static final BigDecimal W2 = new BigDecimal("0.7");

    private final StudentScoreRepository scoreRepository;

    public ScoreService(StudentScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /** final = 0.3 x score1 + 0.7 x score2, rounded to 1 decimal so it fits the grade table */
    public BigDecimal calculateFinal(BigDecimal score1, BigDecimal score2) {
        return score1.multiply(W1).add(score2.multiply(W2)).setScale(1, RoundingMode.HALF_UP);
    }

    /** 8.0-10 = A, 6.0-7.9 = B, 4.0-5.9 = D, below 4.0 = F */
    public String toGrade(BigDecimal finalScore) {
        if (finalScore.compareTo(new BigDecimal("8.0")) >= 0) return "A";
        if (finalScore.compareTo(new BigDecimal("6.0")) >= 0) return "B";
        if (finalScore.compareTo(new BigDecimal("4.0")) >= 0) return "D";
        return "F";
    }

    public StudentScore save(StudentScore score) {
        return scoreRepository.save(score);
    }

    @Transactional(readOnly = true)
    public List<StudentScoreView> findAllViews() {
        return scoreRepository.findAllWithDetails().stream()
                .map(ss -> new StudentScoreView(
                        ss.getId(),
                        ss.getStudent().getStudentCode(),
                        ss.getStudent().getFullName(),
                        ss.getSubject().getSubjectName(),
                        ss.getScore1(),
                        ss.getScore2(),
                        ss.getSubject().getCredit(),
                        toGrade(calculateFinal(ss.getScore1(), ss.getScore2()))))
                .toList();
    }
}