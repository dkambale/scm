package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Institute;
import com.scm.app.repo.InstituteRepo;

@Service
public class InstitueService {

	@Autowired
	InstituteRepo repo;

	public Institute saveInstitute(Institute institute) {

		return repo.save(institute);
	}

	public List<Institute> getAll() {
		
		return repo.findAll();
	}

	public Institute getById(Integer id) {
		Optional<Institute> std = repo.findById(id);
		return std.isPresent()? std.get() : new Institute();
	}
	
	public boolean deleteById(Long id) {
		repo.deleteById(id.intValue());
		return true;
	}

}
