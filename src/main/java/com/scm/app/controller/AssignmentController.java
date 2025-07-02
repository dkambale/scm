package com.scm.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.app.model.Assignment;
import com.scm.app.model.Attendance;
import com.scm.app.model.Student;
import com.scm.app.model.dto.AssignmentDTO;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.service.AssignmentService;
import com.scm.app.service.StudentService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(name = "api/assignments", value = "api/assignments")
@CrossOrigin
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    @Autowired
    private StudentService studentService;

    private final String uploadDir = "uploads/";

    @GetMapping("/getAll/{accountId}")
    public ResponseEntity<List<Assignment>> getAll(@PathVariable Long accountId,
                                                   @PathParam("createdBy") String createdBy) {
        return ResponseEntity.ok(service.getAllByAccountId(accountId,createdBy));
    }

    @GetMapping("/getById")
    public ResponseEntity<Assignment> getById(@RequestParam Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping(name = "/save", value = "/save", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Assignment> saveAssignment(
            @RequestPart("assignment") AssignmentDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        Assignment assignment = new Assignment();
        BeanUtils.copyProperties(dto, assignment);

        if (file != null && !file.isEmpty()) {
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());
            assignment.setFilePath(filePath.toString());
        }

        return ResponseEntity.ok(service.saveAssignment(assignment));
    }

    @PutMapping(name = "/update", value = "/update")
    public ResponseEntity<Assignment> updateAssignment(@RequestBody Assignment assignment) {
        return ResponseEntity.ok(service.updateAssignment(assignment));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/getAll/{accountId}")
    public PaginatedResponse<Assignment> getAll(@PathVariable("accountId") Integer accountId,
                                                @RequestBody PaginationRequest paginationRequest) {
        return service.getAll(paginationRequest, accountId);
    }

    @PostMapping(value = "/uploadForClass", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadForClassAndDivision(
            @RequestPart("assignment") AssignmentDTO dto,
            @RequestPart("file") MultipartFile file) throws IOException {

        Assignment assignment = new Assignment();
        BeanUtils.copyProperties(dto, assignment);
        assignment.setCreatedDate(LocalDateTime.now());

        // Save file
        if (file != null && !file.isEmpty()) {
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            assignment.setFilePath(filePath.toString());
            assignment.setFile(file.getBytes());
        }

        // Save assignment
        Assignment savedAssignment = service.saveAssignment(assignment);

        // Fetch all students from class and division
        List<Student> students = studentService.getByDivisionIdAndClassId(dto.getClassId(), dto.getDivisionId());

        // Here, you can log or simulate that assignment is delivered to students
        students.forEach(student -> {
            System.out.println("Assignment ID: " + savedAssignment.getId() + " sent to Student Roll No: " + student.getRollno());
            // Optional: send notification or create student-assignment mapping if required
        });

        return ResponseEntity.ok("Assignment uploaded for classId: " + dto.getClassId() +
                " and divisionId: " + dto.getDivisionId() +
                ". Assigned to " + students.size() + " students.");
    }



}
