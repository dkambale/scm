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

import com.scm.app.model.SchoolClass;
import com.scm.app.service.SchoolClassService;

@RestController
@RequestMapping(name = "/schoolclass", value = "/schoolclass")

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
	
	@GetMapping("/getall")
	public List<SchoolClass> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getbyid")
	public SchoolClass getById(@RequestParam("id") Integer id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<SchoolClass> updateInstitute(@RequestBody SchoolClass institute) {
		try {
			SchoolClass institue = service.saveClass(institute);
			return new ResponseEntity<SchoolClass>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SchoolClass>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
