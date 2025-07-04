package com.scm.app.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.Course;
import com.scm.app.repo.CourseRepo;

@Service
public class CourseService 
{
	@Autowired
	CourseRepo repo;

	@Autowired
	private AuditLogger auditLogger;

	public Course saveCourse(Course course) {
		Course byUserName = repo.getByName(course.getName());
		if(byUserName!=null) {
			throw new RuntimeException("Course is Already");
		}

		Course saved = repo.save(course);

		//  Audit Log for CREATE
		auditLogger.logAction(
				course.getAccountId(),
				saved.getId(),
				"Course",
				"Create",
				course.getCreatedBy()
		);
		return saved;
	}

	public Course updateCourse(Course course) {
		Course byUserName = repo.getByName(course.getName());
		if(byUserName!=null && !Objects.equals(course.getId(), byUserName.getId())) {
			throw new RuntimeException("Course is Already");
		}

		Course updated = repo.save(course);

		//  Audit Log for UPDATE
		auditLogger.logAction(
				course.getAccountId(),
				updated.getId(),
				"Course",
				"Edit",
				course.getCreatedBy()
		);

		return updated;

	}

	public PaginatedResponse<Course> getAll(PaginationRequest request, Integer accountId) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Course> userPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			userPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<Course> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}

	public Course getById(Long id) {
		Optional<Course> std = repo.findById(id);
		return std.isPresent()? std.get() : new Course();
	}
	

	public boolean deleteById(Long id) {

		Course existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getId(),
					"Course",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}


	}

}
