package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.SchoolClass;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.SchoolBranch;
import com.scm.app.service.SchoolBranchService;

@RestController
@RequestMapping(name = "api/schoolbranches", value = "/schoolbranches")
public class SchoolBranchController {

	@Autowired
	SchoolBranchService service;

	@PostMapping(name = "/save", value = "/save")
	
	public ResponseEntity<SchoolBranch> save(@RequestBody SchoolBranch branch) {

		try {
			SchoolBranch schoolbranch = service.saveBranch(branch);
			return new ResponseEntity<SchoolBranch>(schoolbranch, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SchoolBranch>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAll/{accountId}")
	public PaginatedResponse<SchoolBranch> getAll(@PathVariable("accountId") Integer accountId,
												 @RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}
	
	@GetMapping("/getById")
	public SchoolBranch getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<SchoolBranch> updateInstitute(@RequestBody SchoolBranch institute) {
		try {
			SchoolBranch schoolBranch = service.updateSchoolBranch(institute);
			return new ResponseEntity<SchoolBranch>(schoolBranch, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SchoolBranch>(HttpStatus.INTERNAL_SERVER_ERROR);
			//
		}
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}
	
}