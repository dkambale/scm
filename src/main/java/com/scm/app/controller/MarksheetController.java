package com.scm.app.controller;


import com.scm.app.model.Marksheet;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.service.MarksheetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "api/marksheets", value = "api/marksheets")
@CrossOrigin(origins = "*")
public class MarksheetController {

    @Autowired
    private MarksheetService marksheetService;

    @PostMapping("/save")
    public ResponseEntity<Marksheet> createMarksheet(@RequestBody Marksheet marksheet) {
        return ResponseEntity.ok(marksheetService.saveMarksheet(marksheet));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Marksheet>> getAll() {
        return ResponseEntity.ok(marksheetService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marksheet> getById(@PathVariable Long id) {
        return ResponseEntity.ok(marksheetService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Marksheet> update(@PathVariable Long id, @RequestBody Marksheet marksheet) {
        return ResponseEntity.ok(marksheetService.updateMarksheet(id, marksheet));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        marksheetService.deleteMarksheet(id);
        return ResponseEntity.ok("Marksheet deleted successfully");
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Marksheet>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(marksheetService.getByStudentId(studentId));
    }

    @PostMapping("/paginated")
    public ResponseEntity<PaginatedResponse<Marksheet>> getPaginated(@RequestBody PaginationRequest request) {
        return ResponseEntity.ok(marksheetService.getPaginated(request));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Marksheet>> saveBulk(@RequestBody List<Marksheet> marksheets) {
        return ResponseEntity.ok(marksheetService.saveAll(marksheets));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Marksheet>> searchByAccountAndSubject(
            @RequestParam Long accountId,
            @RequestParam String subject) {
        return ResponseEntity.ok(marksheetService.findByAccountIdAndSubject(accountId, subject));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Marksheet>> getByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(marksheetService.findByAccountId(accountId));
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportData() {
        return ResponseEntity.ok("Exported marksheets successfully");
    }
}
