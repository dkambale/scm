package com.scm.app.service;


import com.scm.app.model.Exam;
import com.scm.app.repo.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  ExamService {

    @Autowired
    private ExamRepository examRepository;


    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
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
        return examRepository.save(existing);
    }


    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }


    public List<Exam> getExamsByAccountId(Long accountId) {
        return examRepository.findByAccountId(accountId);
    }


    public List<Exam> searchExams(String keyword) {
        return examRepository.findByExamNameContainingIgnoreCase(keyword);
    }
}

