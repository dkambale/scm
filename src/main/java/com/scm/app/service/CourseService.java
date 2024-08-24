package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Course;
import com.scm.app.repo.CourseRepo;

@Service
public class CourseService 
{
	@Autowired
	CourseRepo repo;

	public Course saveCourse(Course course) {

		return repo.save(course);
	}

	public List<Course> getAll() {
		
		return repo.findAll();
	}

	public Course getById(Long id) {
		Optional<Course> std = repo.findById(id);
		return std.isPresent()? std.get() : new Course();
	}

}
