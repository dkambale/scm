package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Subject;
import com.scm.app.repo.SubjectRepo;

@Service
public class SubjectService {

	@Autowired
	SubjectRepo repo;

	public Subject saveSubject(Subject std) {
		return repo.save(std);
	}

	public List<Subject> getAll() {
		return repo.findAll();
	}

	public Subject getById(Long id) {
		Optional<Subject> subject = repo.findById(id);
		return subject.isPresent() ? subject.get() : new Subject();
	}

}
