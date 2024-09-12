package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scm.app.model.Teacher;
import com.scm.app.repo.TeacherRepo;

@Service
public class TeacherService {
	
	@Autowired
	TeacherRepo repo;
	
	public Teacher saveTeacher(Teacher std) {
		return repo.save(std);
	}

	public List<Teacher> getAll() {
		return repo.findAll();
	}

	public Teacher getById(Long id) {
		Optional<Teacher> teacher = repo.findById(id);
		return teacher.isPresent()? teacher.get() : new Teacher();
	}

}
