package com.scm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Student;
import com.scm.app.service.StudentService;

@RestController
@RequestMapping(name = "api/student", value = "api/student")

public class StudentController {

	@Autowired
	StudentService service;

	@PostMapping(name = "/save", value = "/save")

	public ResponseEntity<Student> saveStudent(@RequestBody Student std) {

		try {
			Student student = service.saveStudent(std);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Student>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public List<Student> getAll(
			) {
		return service.getAll();
	}

	@GetMapping("/getbyid")
	public Student getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}

	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Student> updateInstitute(@RequestBody Student institute) {
		try {
			Student institue = service.saveInstitute(institute);
			return new ResponseEntity<Student>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Student>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}

	@GetMapping("/getByDivisionIdAndClassIdAndAccountId")
	public ResponseEntity<List<Student>> getByDivisionIdAndClassId(@RequestParam("classId") Long classId,
			@RequestParam("divisionId") Long divisionId,@RequestParam("accountId") Long accountId) {

		try {
			List<Student> institue = service.getByDivisionIdAndClassIdAndAccountId(classId, divisionId,accountId);
			return new ResponseEntity<List<Student>>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Student>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}