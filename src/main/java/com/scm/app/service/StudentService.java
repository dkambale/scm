package com.scm.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Institute;
import com.scm.app.model.Student;
import com.scm.app.repo.InstituteRepo;
import com.scm.app.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo repo;

	public Student saveStudent(Student std) {
		return repo.save(std);
	}
	
	// write method for save,update,getById,getAll
}

