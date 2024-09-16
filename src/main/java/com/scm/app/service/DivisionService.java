package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Division;
import com.scm.app.repo.DivisionRepo;

@Service
public class DivisionService {

	@Autowired
	DivisionRepo repo;

	public Division saveDivision(Division division) {

		return repo.save(division);
	}

	public List<Division> getAll() {

		return repo.findAll();
	}

	public Division getById(Long id) {
		Optional<Division> std = repo.findById(id);
		return std.isPresent() ? std.get() : new Division();
	}
}
