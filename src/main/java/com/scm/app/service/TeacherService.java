package com.scm.app.service;

import java.util.Optional;

import com.scm.app.model.*;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.TimeTableRepo;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.scm.app.repo.TeacherRepo;

@Service
public class TeacherService {
	
	@Autowired
	TeacherRepo repo;

	@Autowired
	private AuditLogger auditLogger;

	public Teacher saveTeacher(Teacher std) {
		Teacher saved = repo.save(std);

		auditLogger.logAction(
				std.getAccountId(),
				saved.getId(),
				"Teacher",
				"Create",
				std.getCreatedBy()
		);


		return saved;

	}

	public PaginatedResponse<Teacher> getAll(PaginationRequest request, Long accountId) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);

		Page<Teacher> tbPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			tbPage= repo.findByUserNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			tbPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<Teacher> response = new PaginatedResponse<>();
		response.setContent(tbPage.getContent());
		response.setPageNumber(tbPage.getNumber());
		response.setPageSize(tbPage.getSize());
		response.setTotalElements(tbPage.getTotalElements());
		response.setTotalPages(tbPage.getTotalPages());
		response.setLastPage(tbPage.isLast());

		return response;
	}

	public Teacher getById(Long id) {
		Optional<Teacher> teacher = repo.findById(id);
		return teacher.isPresent()? teacher.get() : new Teacher();
	}
	
	public boolean deleteById(Long id) {
		Teacher existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getId(),
					"Teacher",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}

	}

}
