package com.scm.app.repo;

import com.scm.app.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.User;

public interface UserRepo extends JpaRepository <User,Long>{

    User getByUserNameAndPassword(String userName, String password);

    User getByUserName(String userName);

    Page<User> findByFirstNameContainingAndAccountId(String search, Long accountId, Pageable pageable);

    Page<User> findByAccountId(Long accountId, Pageable pageable);

    Page<User> findByAccountIdAndType(Long accountId, String type, Pageable pageable);

    Page<User> findByFirstNameContainingAndAccountIdAndType(String search, Long accountId, String type, Pageable pageable);

    Page<User> findByFirstNameContaining(String search, Pageable pageable);

}
