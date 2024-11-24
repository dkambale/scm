package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<TimeTable> getAll() {
		return repo.findAll();
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
