package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Division;

public interface DivisionRepo extends JpaRepository<Division,Integer> {

}
