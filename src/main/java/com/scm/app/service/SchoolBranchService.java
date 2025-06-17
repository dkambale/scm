
package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import com.scm.app.model.SchoolClass;
import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.SchoolBranch;
import com.scm.app.repo.SchoolBranchRepo;

@Service
public class SchoolBranchService {

	@Autowired
	SchoolBranchRepo repo;

	public SchoolBranch saveBranch(SchoolBranch sb) {
		SchoolBranch byName = repo.findByNameAndAccountId(sb.getName(), sb.getAccountId());
		if (byName != null) {
			throw new RuntimeException("SchoolBranch with this name already exists");
		}
		return repo.save(sb);
	}

	public PaginatedResponse<SchoolBranch> getAll(PaginationRequest request, Integer accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<SchoolBranch> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<SchoolBranch> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public SchoolBranch getById(Long id) {
		Optional<SchoolBranch> std = repo.findById(id);
		return std.isPresent()? std.get() : new SchoolBranch();
	}

	public SchoolBranch updateSchoolBranch(SchoolBranch sc) {
		SchoolBranch byName = repo.findByNameAndAccountId(sc.getName(), sc.getAccountId());
		if (byName != null && !byName.getId().equals(sc.getId())) {
			throw new RuntimeException("SchoolBranch with this name already exists");
		}
		return repo.save(sc);
	}


	public boolean deleteById(Long id) {
		repo.deleteById(id);
		return true;
	}
	
}

