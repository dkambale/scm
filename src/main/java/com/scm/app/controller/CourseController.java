package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.Assignment;
import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Course;
import com.scm.app.service.CourseService;

@RestController
@RequestMapping(name = "api/courses", value = "api/courses")
public class CourseController {
    @Autowired
    private CourseService service;

    @PostMapping(name = "/save", value = "/save")
    public ResponseEntity<Course> addCourse(@RequestBody Course cr) {
        try {
            Course course = service.saveCourse(cr);
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Course>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll/{accountId}")
    public PaginatedResponse<Course> getAll(@PathVariable("accountId") Integer accountId,
                                          @RequestBody PaginationRequest paginationRequest) {
        return service.getAll(paginationRequest, accountId);
    }

    @GetMapping("/getById")
    public Course getById(@RequestParam("id") Long id) {
        return service.getById(id);
    }
    @PutMapping(name = "/update", value = "/update")
    public ResponseEntity<Course> updateCourse(@RequestBody Course cr) {
        try {
            Course course = service.updateCourse(cr);
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Course>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") Long id) {
        return service.deleteById(id);
    }

}
