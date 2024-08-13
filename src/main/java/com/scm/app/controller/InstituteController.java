package com.scm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Institute;
import com.scm.app.service.InstitueService;

@RestController
@RequestMapping(name = "/institute", value = "/institute")
public class InstituteController {

	@Autowired
	InstitueService service;

	@PostMapping(name = "/add", value = "/add")
	
	public ResponseEntity<Institute> saveInstitute(@RequestBody Institute institute) {

		try {
			Institute institue = service.saveInstitute(institute);
			return new ResponseEntity<Institute>(institue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Institute>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
