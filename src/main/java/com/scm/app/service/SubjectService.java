package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import com.scm.app.model.Course;
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

import com.scm.app.model.Subject;
import com.scm.app.repo.SubjectRepo;

@Service
public class SubjectService {

	@Autowired
	SubjectRepo repo;

	@Autowired
	private AuditLogger auditLogger;

	public Subject saveSubject(Subject std) {
		Subject byName = repo.findByNameAndAccountId(std.getName(), std.getAccountId());
		if (byName != null) {
			throw new RuntimeException("Subject with this name already exists");
		}
		Subject saved = repo.save(std);

		//  Audit Log for CREATE
		auditLogger.logAction(
				std.getAccountId(),
				saved.getId(),
				"Subject",
				"Create",
				std.getCreatedBy()
		);
		return saved;

	}

	public Subject updateSubject(Subject std) {
		Subject byName = repo.findByNameAndAccountId(std.getName(), std.getAccountId());
		if (byName != null && !byName.getId().equals(std.getId())) {
			throw new RuntimeException("Subject with this name already exists");
		}
		Subject updated = repo.save(std);

		//  Audit Log for UPDATE
		auditLogger.logAction(
				std.getAccountId(),
				updated.getId(),
				"Subject",
				"Edit",
				std.getCreatedBy()
		);

		return updated;

	}

	public PaginatedResponse<Subject> getAll(PaginationRequest request, Long accountId) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Subject> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<Subject> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public Subject getById(Long id) {
		Optional<Subject> subject = repo.findById(id);
		return subject.isPresent() ? subject.get() : new Subject();
	}
	
	public boolean deleteById(Long id) {
		Subject existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getId(),
					"Subject",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}

	}

}
