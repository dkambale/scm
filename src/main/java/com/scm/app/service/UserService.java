package com.scm.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.scm.app.model.Course;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.util.AuditLogger;
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

	@Autowired
	private AuditLogger auditLogger;
	
	public User saveUser(User usr) {

		User byUserName = repo.getByUserName(usr.getUserName());
		if(byUserName!=null) {
			throw new RuntimeException("User is Already");
		}
		User saved = repo.save(usr);

		//  Audit Log for CREATE
		auditLogger.logAction(
				usr.getAccountId(),
				saved.getId(),
				"User",
				"Create",
				usr.getCreatedBy()
		);
		return saved;


	}

	public User updateUser(User usr) {

		User byUserName = repo.getByUserName(usr.getUserName());
		if(byUserName!=null && !Objects.equals(usr.getId(), byUserName.getId())) {
			throw new RuntimeException("User is Already");
		}
		User updated = repo.save(usr);

		//  Audit Log for UPDATE
		auditLogger.logAction(
				usr.getAccountId(),
				updated.getId(),
				"User",
				"Edit",
				usr.getCreatedBy()
		);

		return updated;


	}

	public PaginatedResponse<User> getAll(PaginationRequest request, Long accountId,String type) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<User> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByFirstNameContainingAndAccountIdAndType(request.getSearch(),accountId,type, pageable);
		} else {
			userPage =repo.findByAccountIdAndType(accountId,type,pageable);
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
		User existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getId(),
					"User",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}


	}
}
