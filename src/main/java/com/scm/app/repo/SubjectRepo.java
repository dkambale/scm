package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
	

}
