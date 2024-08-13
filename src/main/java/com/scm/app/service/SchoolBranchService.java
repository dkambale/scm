
package com.scm.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Institute;
import com.scm.app.model.SchoolBranch;
import com.scm.app.repo.InstituteRepo;
import com.scm.app.repo.SchoolBranchRepo;

@Service
public class SchoolBranchService {

	@Autowired
	SchoolBranchRepo repo;

	public SchoolBranch saveBranch(SchoolBranch sb) {
		
		return repo.save(sb);
	}
	
	// write method for save,update,getById,getAll
}

