package com.scm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Institute;
import com.scm.app.model.SchoolBranch;
import com.scm.app.service.InstitueService;
import com.scm.app.service.SchoolBranchService;

@RestController
@RequestMapping(name = "/institute", value = "/institute")
// toto change this to school branch
public class SchoolBranchController {

	@Autowired
	SchoolBranchService service;

	@PostMapping(name = "/addbranch", value = "/addbranch")
	// todo use only save, update , delete , getAll , getById endpoints
	public ResponseEntity<SchoolBranch> saveBranch(@RequestBody SchoolBranch branch) {

		try {
			SchoolBranch schoolbranch = service.saveBranch(branch);
			return new ResponseEntity<SchoolBranch>(schoolbranch, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SchoolBranch>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// todo add CRUD endpoint for institute
	
}