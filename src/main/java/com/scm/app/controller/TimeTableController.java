package com.scm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.TimeTable;
import com.scm.app.service.TimeTableService;

@RestController
@RequestMapping(name = "/timetable", value = "/timetable")
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

	@GetMapping("/getall")
	public List<TimeTable> getAll() {
		return service.getAll();
	}

	@GetMapping("/getbyid")
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
