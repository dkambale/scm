package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student getByUserNameAndPassword(String userName, String password);

}
