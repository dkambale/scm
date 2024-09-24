package com.scm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Subject;
import com.scm.app.service.SubjectService;

@RestController
@RequestMapping(name = "/subject", value = "/subject")
public class SubjectController {

	@Autowired
	SubjectService service;

	@PostMapping(name = "/save", value = "/save")

	public ResponseEntity<Subject> savesubject(@RequestBody Subject tea) {

		try {
			Subject subject = service.saveSubject(tea);
			return new ResponseEntity<Subject>(subject, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Subject>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public List<Subject> getAll() {
		return service.getAll();
	}

	@GetMapping("/getbyid")
	public Subject getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}

	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Subject> updatesubject(@RequestBody Subject subject) {
		try {
			Subject subject1 = service.saveSubject(subject);
			return new ResponseEntity<Subject>(subject1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Subject>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
