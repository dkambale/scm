package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.Institute;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Division;
import com.scm.app.service.DivisionService;

@RestController
@RequestMapping(name = "api/divisions", value = "api/divisions")
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

	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<Division> getAll(@PathVariable("accountId") Integer accountId,
											   @RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}

	@GetMapping("/getById")
	public Division getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}

	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Division> updateDivision(@RequestBody Division cr) {
		try {
			Division division = service.updateInstitute(cr);
			return new ResponseEntity<Division>(division, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Division>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}
}
