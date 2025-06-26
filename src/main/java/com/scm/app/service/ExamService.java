package com.scm.app.service;


import com.scm.app.model.Assignment;
import com.scm.app.model.AuditLog;
import com.scm.app.model.Course;
import com.scm.app.model.Exam;
import com.scm.app.repo.ExamRepository;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class  ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private AuditLogger auditLogger;

    public Exam createExam(Exam exam) {
        Exam saved = examRepository.save(exam);

        //  Audit Log for CREATE
        auditLogger.logAction(
                exam.getAccountId(),
                saved.getId(),
                "Exam",
                "Create",
                exam.getCreatedBy()
        );
        return saved;
    }


    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }


    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElseThrow(() -> new RuntimeException("Exam not found"));
    }


    public Exam updateExam(Long id, Exam updatedExam) {
        Exam existing = getExamById(id);
        existing.setExamName(updatedExam.getExamName());
        existing.setSubject(updatedExam.getSubject());
        existing.setExamDate(updatedExam.getExamDate());
        existing.setExamType(updatedExam.getExamType());
        existing.setTotalMarks(updatedExam.getTotalMarks());
        existing.setAccountId(updatedExam.getAccountId());
        Exam updated = examRepository.save(existing);


        //  Audit Log for UPDATE
        auditLogger.logAction(
                updatedExam.getAccountId(),
                updated.getId(),
                "Exam",
                "Edit",
                updatedExam.getCreatedBy()
        );



        return updated;
    }


    public void deleteExam(Long id) {
        Exam existing = examRepository.findById(id).orElse(null);
        if (existing != null) {
            examRepository.deleteById(id);

            //  Audit Log for DELETE
            auditLogger.logAction(
                    existing.getAccountId(),
                    existing.getId(),
                    "Exam",
                    "Delete",
                    existing.getCreatedBy()
            );
        }



    }


    public List<Exam> getExamsByAccountId(Long accountId) {
        return examRepository.findByAccountId(accountId);
    }


    public List<Exam> searchExams(String keyword) {
        return examRepository.findByExamNameContainingIgnoreCase(keyword);
    }
}

