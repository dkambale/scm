package com.scm.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
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

	public Course saveCourse(Course course) {
		Course byUserName = repo.getByName(course.getName());
		if(byUserName!=null) {
			throw new RuntimeException("Course is Already");
		}

		return repo.save(course);
	}

	public Course updateCourse(Course course) {
		Course byUserName = repo.getByName(course.getName());
		if(byUserName!=null && !Objects.equals(course.getId(), byUserName.getId())) {
			throw new RuntimeException("Course is Already");
		}

		return repo.save(course);
	}

	public PaginatedResponse<Course> getAll(PaginationRequest request, Integer accountId) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Course> userPage = repo.findByNameContainingAndAccountId(request.getSearch(),accountId, pageable);

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
		repo.deleteById(id);
		return true;
	}

}
