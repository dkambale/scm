package com.scm.app.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.scm.app.model.*;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.repo.DivisionRepo;

@Service
public class DivisionService {

	@Autowired
	DivisionRepo repo;

	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private AuditLogger auditLogger;
	public Division saveDivision(Division division) {
		Division byUserName = repo.getByName(division.getName());
		if(byUserName!=null) {
			throw new RuntimeException("Division is Already");
		}



		Division saved = repo.save(division);

		//  Audit Log for CREATE
		auditLogger.logAction(
				division.getAccountId(),
				saved.getId(),
				"Division",
				"Create",
				division.getCreatedBy()
		);

		return saved;
	}


	public PaginatedResponse<Division> getAll(PaginationRequest request, Integer accountId) {
		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Division> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

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

		Division existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getId(),
					"Division",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}

	}

	public Division updateInstitute(Division division) {
		Division byName = repo.getByName(division.getName());
		if(byName!=null && !Objects.equals(division.getId(), byName.getId())) {
			throw new RuntimeException("Division is Already");
		}

		Division updated = repo.save(division);

		//  Audit Log for UPDATE
		auditLogger.logAction(
				division.getAccountId(),
				updated.getId(),
				"Division",
				"Edit",
				division.getCreatedBy()
		);

		return updated;

	}
}
