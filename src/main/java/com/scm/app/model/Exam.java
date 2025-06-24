package com.scm.app.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examName;
    private String subject;
    private LocalDate examDate;
    private String examType;
    private Integer totalMarks;
    private Long accountId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getExamName() { return examName; }
    public void setExamName(String examName) { this.examName = examName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }

    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }

    public Integer getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Integer totalMarks) { this.totalMarks = totalMarks; }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
}

