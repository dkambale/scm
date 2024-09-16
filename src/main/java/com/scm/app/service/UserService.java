package com.scm.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.User;
import com.scm.app.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo repo;
	
	public User saveUser(User usr) {
		return repo.save(usr);
	}

	public List<User> getAll() {
		return repo.findAll();
	}

	public User getById(Long id) {
		Optional<User> usr = repo.findById(id);
		return usr.isPresent()? usr.get() : new User();
	}
	
}
