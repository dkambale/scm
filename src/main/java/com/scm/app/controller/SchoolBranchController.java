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

import com.scm.app.model.SchoolBranch;
import com.scm.app.service.SchoolBranchService;

@RestController
@RequestMapping(name = "/schoolbranch", value = "/schoolbranch")
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
	
	@GetMapping("/getall")
	public List<SchoolBranch> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getbyid")
	public SchoolBranch getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<SchoolBranch> updateInstitute(@RequestBody SchoolBranch institute) {
		try {
			SchoolBranch institue = service.saveInstitute(institute);
			return new ResponseEntity<SchoolBranch>(institue, HttpStatus.OK);
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