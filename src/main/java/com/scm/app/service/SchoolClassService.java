package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Institute;
import com.scm.app.model.SchoolClass;
import com.scm.app.repo.InstituteRepo;
import com.scm.app.repo.SchoolClassRepo;

@Service
public class SchoolClassService {

	@Autowired
	SchoolClassRepo repo;

	public SchoolClass saveClass(SchoolClass sc) {
		return repo.save(sc);
	}

	public List<SchoolClass> getAll() {
		return repo.findAll();
	}

	public SchoolClass getById(Integer id) {
		Optional<SchoolClass> std = repo.findById(id);
		return std.isPresent()? std.get() : new SchoolClass();
	}

}

