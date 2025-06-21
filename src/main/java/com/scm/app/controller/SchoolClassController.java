package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.Subject;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.SchoolClass;
import com.scm.app.service.SchoolClassService;

@RestController
@RequestMapping(name = "api/schoolClasses", value = "api/schoolClasses")

public class SchoolClassController {

	@Autowired
	SchoolClassService service;

	@PostMapping(name = "/save", value = "/save")
	public ResponseEntity<SchoolClass> saveClass(@RequestBody SchoolClass sc) {

		try {
			SchoolClass schoolclass = service.saveClass(sc);
			return new ResponseEntity<SchoolClass>(schoolclass, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SchoolClass>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<SchoolClass> getAll(@PathVariable("accountId") Integer accountId,
											 @RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}
	
	@GetMapping("/getById")
	public SchoolClass getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<SchoolClass> updateInstitute(@RequestBody SchoolClass institute) {
		try {
			SchoolClass institue = service.updateClass(institute);
			return new ResponseEntity<SchoolClass>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SchoolClass>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}
}
