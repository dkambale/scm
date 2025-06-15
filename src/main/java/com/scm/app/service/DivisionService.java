package com.scm.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.scm.app.model.Institute;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.Division;
import com.scm.app.repo.DivisionRepo;

@Service
public class DivisionService {

	@Autowired
	DivisionRepo repo;

	public Division saveDivision(Division division) {
		Division byUserName = repo.getByName(division.getName());
		if(byUserName!=null) {
			throw new RuntimeException("Division is Already");
		}

		return repo.save(division);
	}


	public PaginatedResponse<Division> getAll(PaginationRequest request, Integer accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Division> userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);

		PaginatedResponse<Division> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public Division getById(Long id) {
		Optional<Division> std = repo.findById(id);
		return std.isPresent() ? std.get() : new Division();
	}
	
	public boolean deleteById(Long id) {
		repo.deleteById(id);
		return true;
	}

	public Division updateInstitute(Division division) {
		Division byName = repo.getByName(division.getName());
		if(byName!=null && !Objects.equals(division.getId(), byName.getId())) {
			throw new RuntimeException("Division is Already");
		}

		return repo.save(division);
	}
}
