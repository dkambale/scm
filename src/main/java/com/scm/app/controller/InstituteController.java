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

import com.scm.app.model.Institute;
import com.scm.app.service.InstitueService;

@RestController
@RequestMapping(name = "/institute", value = "/institute")
public class InstituteController {

	@Autowired
	InstitueService service;

	@PostMapping(name = "/save", value = "/save")
	
	public ResponseEntity<Institute> addInstitute(@RequestBody Institute institute) {

		try {
			Institute institue = service.saveInstitute(institute);
			return new ResponseEntity<Institute>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Institute>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getall")
	public List<Institute> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getbyid")
	public Institute getById(@RequestParam("id") Integer id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Institute> updateInstitute(@RequestBody Institute institute) {
		try {
			Institute institue = service.saveInstitute(institute);
			return new ResponseEntity<Institute>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Institute>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
