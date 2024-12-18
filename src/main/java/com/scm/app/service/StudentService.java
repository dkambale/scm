package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Student;
import com.scm.app.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo repo;

	public Student saveStudent(Student std) {
		return repo.save(std);
	}

	public List<Student> getAll() {
		return repo.findAll();
	}

	public Student getById(Long id) {
		Optional<Student> std = repo.findById(id);
		return std.isPresent()? std.get() : new Student();
	}

	public Student saveInstitute(Student student) {
		return repo.save(student);
	}
	
	
	public boolean deleteById(Long id) {
		repo.deleteById(id);
		return true;
	}

	public List<Student> getByDivisionIdAndClassId(Long classId, Long divisionId) {
		return repo.getByDivisionIdAndClassId(divisionId, classId);
	}
	
}
