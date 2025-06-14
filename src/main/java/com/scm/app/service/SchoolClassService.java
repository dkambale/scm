package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import com.scm.app.model.Subject;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.SchoolClass;
import com.scm.app.repo.SchoolClassRepo;

@Service
public class SchoolClassService {

	@Autowired
	SchoolClassRepo repo;

	public SchoolClass saveClass(SchoolClass sc) {
		SchoolClass byName = repo.findByNameAndAccountId(sc.getName(), sc.getAccountId());
		if (byName != null) {
			throw new RuntimeException("SchoolClass with this name already exists");
		}
		return repo.save(sc);
	}

	public SchoolClass updateClass(SchoolClass sc) {
		SchoolClass byName = repo.findByNameAndAccountId(sc.getName(), sc.getAccountId());
		if (byName != null && !byName.getId().equals(sc.getId())) {
			throw new RuntimeException("SchoolClass with this name already exists");
		}
		return repo.save(sc);
	}


	public PaginatedResponse<SchoolClass> getAll(PaginationRequest request, Integer accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<SchoolClass> userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);

		PaginatedResponse<SchoolClass> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public SchoolClass getById(Long id) {
		Optional<SchoolClass> std = repo.findById(id);
		return std.isPresent()? std.get() : new SchoolClass();
	}
	
	public boolean deleteById(Long id) {
		repo.deleteById(id);
		return true;
	}

}

