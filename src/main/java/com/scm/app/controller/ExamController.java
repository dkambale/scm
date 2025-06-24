package com.scm.app.controller;


import com.scm.app.model.Exam;
import com.scm.app.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name ="/api/exams", value = "/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam savedExam = examService.createExam(exam);
        return ResponseEntity.ok(savedExam);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        return ResponseEntity.ok(examService.updateExam(id, exam));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.ok("Exam deleted successfully");
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Exam>> getExamsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(examService.getExamsByAccountId(accountId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Exam>> searchExams(@RequestParam String keyword) {
        return ResponseEntity.ok(examService.searchExams(keyword));
    }
}

