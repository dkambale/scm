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

import com.scm.app.model.Teacher;
import com.scm.app.service.TeacherService;

@RestController
@RequestMapping(name = "/teacher", value = "/teacher")

public class TeacherController {

	@Autowired
	TeacherService service;

	@PostMapping(name = "/save", value = "/save")

	public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher tea) {

		try {
			Teacher teacher = service.saveTeacher(tea);
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Teacher>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public List<Teacher> getAll() {
		return service.getAll();
	}

	@GetMapping("/getbyid")
	public Teacher getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}

	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
		try {
			Teacher teacher1 = service.saveTeacher(teacher);
			return new ResponseEntity<Teacher>(teacher1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Teacher>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
