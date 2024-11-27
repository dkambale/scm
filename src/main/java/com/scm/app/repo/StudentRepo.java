package com.scm.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

	Student getByUserNameAndPassword(String userName, String password);

	List<Student> getByDivisionIdAndClassId(Long divisionId, Long classId);

}
