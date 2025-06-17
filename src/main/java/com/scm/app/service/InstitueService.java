package com.scm.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.scm.app.model.Review;
import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.Institute;
import com.scm.app.repo.InstituteRepo;

@Service
public class InstitueService {

	@Autowired
	InstituteRepo repo;

	public Institute saveInstitute(Institute institute) {
		Institute byUserName = repo.getByName(institute.getName());
		if(byUserName!=null) {
			throw new RuntimeException("User is Already");
		}

		return repo.save(institute);
	}


	public PaginatedResponse<Institute> getAll(PaginationRequest request, Integer accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Institute> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<Institute> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}


	public Institute getById(Integer id) {
		Optional<Institute> std = repo.findById(id);
		return std.isPresent()? std.get() : new Institute();
	}
	
	public boolean deleteById(Long id) {
		repo.deleteById(id.intValue());
		return true;
	}

	public Institute updateInstitute(Institute institute) {
		Institute byName = repo.getByName(institute.getName());
		if(byName!=null && !Objects.equals(institute.getId(), byName.getId())) {
			throw new RuntimeException("Institute is Already");
		}

		return repo.save(institute);
	}
}
