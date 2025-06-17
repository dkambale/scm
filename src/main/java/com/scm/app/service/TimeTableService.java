package com.scm.app.service;

import java.util.List;
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

import com.scm.app.model.TimeTable;
import com.scm.app.repo.TimeTableRepo;

@Service
public class TimeTableService {

	@Autowired
	TimeTableRepo repo;

	public TimeTable saveTimeTable(TimeTable timeTable) {
		
		timeTable.setDayTimeTable(timeTable.getDayTimeTable());

		return repo.save(timeTable);
	}

	public PaginatedResponse<TimeTable> getAll(PaginationRequest request, Integer accountId) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortDir()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<TimeTable> tbPage =null;
		if(request.getSearch()!= null && request.getSearch().isEmpty()) {
			tbPage = repo.findByClassNameContainingAndAccountId(request.getSearch(),accountId, pageable);
		} else {
			tbPage =repo.findByAccountId(accountId,pageable);
		}

		PaginatedResponse<TimeTable> response = new PaginatedResponse<>();
		response.setContent(tbPage.getContent());
		response.setPageNumber(tbPage.getNumber());
		response.setPageSize(tbPage.getSize());
		response.setTotalElements(tbPage.getTotalElements());
		response.setTotalPages(tbPage.getTotalPages());
		response.setLastPage(tbPage.isLast());

		return response;
	}

	public TimeTable getById(Long id) {
		Optional<TimeTable> teacher = repo.findById(id);
		return teacher.isPresent() ? teacher.get() : null;
	}

	public boolean deleteById(Long id) {
		repo.deleteById(id);
		return true;
	}
}
