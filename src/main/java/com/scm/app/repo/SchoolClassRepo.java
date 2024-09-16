package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.SchoolClass;

public interface SchoolClassRepo extends JpaRepository<SchoolClass,Long> {

}
