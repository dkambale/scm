
package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.SchoolBranch;
import com.scm.app.repo.SchoolBranchRepo;

@Service
public class SchoolBranchService {

	@Autowired
	SchoolBranchRepo repo;

	public SchoolBranch saveBranch(SchoolBranch sb) {
		
		return repo.save(sb);
	}

	public List<SchoolBranch> getAll() {
		return repo.findAll();
	}

	public SchoolBranch getById(Integer id) {
		Optional<SchoolBranch> std = repo.findById(id);
		return std.isPresent()? std.get() : new SchoolBranch();
	}

	public SchoolBranch saveInstitute(SchoolBranch sc) {
		return repo.save(sc);
	}
	
}

