package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import com.scm.app.model.Course;
import com.scm.app.model.Subject;
import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.util.AuditLogger;
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

	@Autowired
	private AuditLogger auditLogger;

	public SchoolClass saveClass(SchoolClass sc) {
		SchoolClass byName = repo.findByNameAndAccountId(sc.getName(), sc.getAccountId());
		if (byName != null) {
			throw new RuntimeException("SchoolClass with this name already exists");
		}
		SchoolClass saved = repo.save(sc);

		//  Audit Log for CREATE
		auditLogger.logAction(
				sc.getAccountId(),
				saved.getId(),
				"Course",
				"Create",
				sc.getCreatedBy()
		);
		return saved;
	}

	public SchoolClass updateClass(SchoolClass sc) {
		SchoolClass byName = repo.findByNameAndAccountId(sc.getName(), sc.getAccountId());
		if (byName != null && !byName.getId().equals(sc.getId())) {
			throw new RuntimeException("SchoolClass with this name already exists");
		}
		SchoolClass updated = repo.save(sc);

		//  Audit Log for UPDATE
		auditLogger.logAction(
				sc.getAccountId(),
				updated.getId(),
				"Course",
				"Edit",
				sc.getCreatedBy()
		);

		return updated;

	}


	public PaginatedResponse<SchoolClass> getAll(PaginationRequest request, Long accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);

		Page<SchoolClass> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

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
		SchoolClass existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getId(),
					"SchoolClass ",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}

	}

}

