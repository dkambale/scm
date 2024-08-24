package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.User;

public interface UserRepo extends JpaRepository <User,Integer>{

}
