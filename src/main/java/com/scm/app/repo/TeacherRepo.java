package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher,Long>{
	
	
	Teacher getByUserNameAndPassword(String userName, String password);

}
