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

import com.scm.app.model.Division;
import com.scm.app.service.DivisionService;

@RestController
@RequestMapping(name = "/division", value = "/division")
public class DivisionController {
	@Autowired
	private DivisionService service;

	@PostMapping(name = "/save", value = "/save")

	public ResponseEntity<Division> addDivision(@RequestBody Division cr) {

		try {
			Division division = service.saveDivision(cr);
			return new ResponseEntity<Division>(division, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Division>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public List<Division> getAll() {
		return service.getAll();
	}

	@GetMapping("/getbyid")
	public Division getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}

	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Division> updateDivision(@RequestBody Division cr) {
		try {
			Division division = service.saveDivision(cr);
			return new ResponseEntity<Division>(division, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Division>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
