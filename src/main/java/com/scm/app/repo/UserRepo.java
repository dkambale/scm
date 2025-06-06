package com.scm.app.repo;

import com.scm.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.User;

public interface UserRepo extends JpaRepository <User,Long>{

    User getByUserNameAndPassword(String userName, String password);
}
