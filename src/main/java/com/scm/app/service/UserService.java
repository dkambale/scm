package com.scm.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.User;
import com.scm.app.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo repo;
	
	public User saveUser(User usr) {

		User byUserName = repo.getByUserName(usr.getUserName());
		if(byUserName!=null) {
			throw new RuntimeException("User is Already");
		}

		return repo.save(usr);
	}

	public User updateUser(User usr) {

		User byUserName = repo.getByUserName(usr.getUserName());
		if(byUserName!=null && !Objects.equals(usr.getId(), byUserName.getId())) {
			throw new RuntimeException("User is Already");
		}

		return repo.save(usr);
	}

	public PaginatedResponse<User> getAll(PaginationRequest request, Integer accountId) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<User> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByFirstNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<User> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public User getById(Long id) {
		Optional<User> usr = repo.findById(id);
		return usr.isPresent()? usr.get() : new User();
	}
	
	public boolean deleteById(Long id) {
		repo.deleteById(id);
		return true;
	}
}
