package com.scm.app.service;

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

	// write method for save,update,getById,getAll
}

