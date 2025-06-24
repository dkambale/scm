package com.scm.app.repo;


import com.scm.app.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByAccountId(Long accountId);
    List<Exam> findByExamNameContainingIgnoreCase(String keyword);
}

