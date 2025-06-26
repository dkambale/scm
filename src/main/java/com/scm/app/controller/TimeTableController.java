package com.scm.app.controller;

import java.util.List;

import com.scm.app.model.User;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.TimeTable;
import com.scm.app.service.TimeTableService;

@RestController
@RequestMapping(name = "api/timetable", value = "api/timetable")
public class TimeTableController {

	@Autowired
	TimeTableService service;

	@PostMapping(name = "/save", value = "/save")
	public ResponseEntity<TimeTable> saveTimeTable(@RequestBody TimeTable usr) {

		try {
			TimeTable timeTable = service.saveTimeTable(usr);
			return new ResponseEntity<TimeTable>(timeTable, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TimeTable>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<TimeTable> getAll(@PathVariable("accountId") Long accountId,
										  @RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}

	@GetMapping("/getById")
	public TimeTable getById(@RequestParam("id") Long id) {
		return service.getById(id);
	}

	@PutMapping(name = "/update", value = "/update")
	public ResponseEntity<TimeTable> updateInstitute(@RequestBody TimeTable usr1) {
		try {
			TimeTable timeTable1 = service.saveTimeTable(usr1);
			return new ResponseEntity<TimeTable>(timeTable1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TimeTable>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") Long id) {
		return service.deleteById(id);
	}

}
