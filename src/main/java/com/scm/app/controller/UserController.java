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

import com.scm.app.model.User;
import com.scm.app.service.UserService;

	@RestController
	@RequestMapping(name = "/user", value = "/user")
	public class UserController {

		@Autowired
		UserService service;

		@PostMapping(name = "/save", value = "/save")
		public ResponseEntity<User> saveUser(@RequestBody User usr) {

			try {
				User user = service.saveUser(usr);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		@GetMapping("/getall")
		public List<User> getAll(){
			return service.getAll();
		}
		
		@GetMapping("/getbyid")
		public User getById(@RequestParam("id") Long id) {
			return service.getById(id);
		}
		
		@PutMapping(name = "/update", value = "/update")
		public ResponseEntity<User> updateInstitute(@RequestBody User usr1) {
			try {
				User user1 = service.saveUser(usr1);
				return new ResponseEntity<User>(user1, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
