package com.scm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Role;
import com.scm.app.service.RoleService;

@RestController
@RequestMapping(name = "/role", value = "/role")
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
	
	@GetMapping("/getall")
	public List<Role> getAll(){
		return service.getAll();
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
