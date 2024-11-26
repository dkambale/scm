package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Role;
import com.scm.app.repo.RoleRepo;

@Service
public class RoleService {

	@Autowired
	RoleRepo repo;

	public Role saveBranch(Role sb) {

		return repo.save(sb);
	}

	public List<Role> getAll() {
		return repo.findAll();
	}

	public Role getById(Long id) {
		Optional<Role> std = repo.findById(id);
		return std.isPresent() ? std.get() : new Role();
	}

	public Role saveInstitute(Role sc) {
		return repo.save(sc);
	}

	public boolean deleteById(Long id) {
		repo.deleteById(id);
		return true;
	}

}
