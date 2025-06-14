package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Teacher;
import com.scm.app.service.TeacherService;

@RestController
@RequestMapping(name = "api/teachers", value = "api/teachers")
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

	@GetMapping("/getAll/{accountId}")
	public PaginatedResponse<Teacher> getAll(@PathVariable("accountId") Integer accountId,
										  @RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}

	@GetMapping("/getById")
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
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}
}
