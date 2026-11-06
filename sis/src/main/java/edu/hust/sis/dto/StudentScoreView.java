package edu.hust.sis.dto;

import java.math.BigDecimal;

public class StudentScoreView {
    private final Integer id;
    private final String studentCode;
    private final String studentName;
    private final String subjectName;
    private final BigDecimal score1;
    private final BigDecimal score2;
    private final int credit;
    private final String grade;

    public StudentScoreView(Integer id, String studentCode, String studentName, String subjectName,
                            BigDecimal score1, BigDecimal score2, int credit, String grade) {
        this.id = id;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.score1 = score1;
        this.score2 = score2;
        this.credit = credit;
        this.grade = grade;
    }

    public Integer getId() { return id; }
    public String getStudentCode() { return studentCode; }
    public String getStudentName() { return studentName; }
    public String getSubjectName() { return subjectName; }
    public BigDecimal getScore1() { return score1; }
    public BigDecimal getScore2() { return score2; }
    public int getCredit() { return credit; }
    public String getGrade() { return grade; }
}