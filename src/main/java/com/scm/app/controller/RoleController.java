package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.SchoolBranch;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Role;
import com.scm.app.service.RoleService;

@RestController
@RequestMapping(name = "api/roles", value = "api/roles")
public class RoleController {

	@Autowired
	RoleService service;

	@PostMapping(name = "/save", value = "/save")
	
	public ResponseEntity<Role> save(@RequestBody Role role) {

		try {
			Role Role = service.saveBranch(role);
			return new ResponseEntity<Role>(Role, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<Role> getAll(@PathVariable("accountId") Long accountId,
												  @RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}
	
	@GetMapping("/getbyid")
	public Role getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Role> updateInstitute(@RequestBody Role institute) {
		try {
			Role institue = service.saveInstitute(institute);
			return new ResponseEntity<Role>(institue, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
			//
		}
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}
	
}
