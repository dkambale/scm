package com.scm.app.repo;

import com.scm.app.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.User;

public interface UserRepo extends JpaRepository <User,Long>{

    User getByUserNameAndPassword(String userName, String password);

    User getByUserName(String userName);

    Page<User> findByFirstNameContainingAndAccountId(String search, Integer accountId, Pageable pageable);

    Page<User> findByAccountId(Integer accountId, Pageable pageable);

}
