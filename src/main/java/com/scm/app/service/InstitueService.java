package com.scm.app.service;

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
}
