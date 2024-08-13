package com.scm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Institute;
import com.scm.app.model.SchoolClass;
import com.scm.app.service.InstitueService;
import com.scm.app.service.SchoolClassService;

@RestController
@RequestMapping(name = "/institute", value = "/institute")
public class SchoolClassController {

	@Autowired
	SchoolClassService service;

	@PostMapping(name = "/addclass", value = "/addclass")
	
	public ResponseEntity<SchoolClass> saveClass(@RequestBody SchoolClass sc) {

		try {
			SchoolClass schoolclass = service.saveClass(sc);
			return new ResponseEntity<SchoolClass>(schoolclass, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SchoolClass>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
