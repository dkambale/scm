package com.scm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.DayTimeTable;

public interface DayTimeTableRepo extends JpaRepository<DayTimeTable, Long> {

}
