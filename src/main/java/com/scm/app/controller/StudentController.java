package com.scm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Student;
import com.scm.app.service.StudentService;

@RestController
@RequestMapping(name = "/institute", value = "/institute")
// change end point
public class StudentController {

	@Autowired
	StudentService service;

	@PostMapping(name = "/addstudent", value = "/addstudent")
	// todo use only save, update , delete , getAll , getById endpoints
	public ResponseEntity<Student> saveStudent(@RequestBody Student std) {

		try {
			Student student = service.saveStudent(std);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Student>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
