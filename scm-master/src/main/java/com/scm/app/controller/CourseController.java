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

import com.scm.app.model.Course;
import com.scm.app.service.CourseService;

@RestController
@RequestMapping(name = "/course", value = "/course")
public class CourseController 
{
	@Autowired
	private CourseService service;
	
	@PostMapping(name = "/save", value = "/save")
	
	public ResponseEntity<Course> addCourse(@RequestBody Course cr) {

		try {
			Course course = service.saveCourse(cr);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Course>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getall")
	public List<Course> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getbyid")
	public Course getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}
	
	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<Course> updateCourse(@RequestBody Course cr) {
		try {
			Course course = service.saveCourse(cr);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Course>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
