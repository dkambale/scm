package com.scm.app.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "marksheets")
public class Marksheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long accountId;
    private String studentName;
    private String subject;
    private Integer marksObtained;
    private Integer totalMarks;
    private Date examDate;
    private String createdBy;
    private String modifiedBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    // Constructors
    public Marksheet() {}

    public Marksheet(Long studentId, Long accountId, String studentName, String subject,
                     Integer marksObtained, Integer totalMarks, Date examDate) {
        this.studentId = studentId;
        this.accountId = accountId;
        this.studentName = studentName;
        this.subject = subject;
        this.marksObtained = marksObtained;
        this.totalMarks = totalMarks;
        this.examDate = examDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Integer marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
}

