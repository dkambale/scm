package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.TimeTable;

public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {

}
