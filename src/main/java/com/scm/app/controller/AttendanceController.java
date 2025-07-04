package com.scm.app.controller;

import java.util.Date;
import java.util.List;

import com.scm.app.model.Course;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.ClassDivisionResponse;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.Attendance;
import com.scm.app.model.requests.StudentAttendanceRequest;
import com.scm.app.model.requests.StudentAttendanceUpdateRequest;
import com.scm.app.model.response.StudentAttendanceResponse;
import com.scm.app.service.AttendanceService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(name = "api/attendance", value = "api/attendance")
public class AttendanceController {

	@Autowired
	AttendanceService service;

	@PostMapping(name = "/updateBulk", value = "/updateBulk")
	public ResponseEntity<Boolean> updateBulkAttendance(@RequestBody StudentAttendanceRequest attendanceRequest) {

		Boolean result = service.updateBulkAttendance(attendanceRequest);

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}

	@PostMapping(name = "/updateStudent/{accountId}", value = "/updateStudent/{accountId}")
	public ResponseEntity<Boolean> updateStudent(@RequestBody StudentAttendanceUpdateRequest attendanceRequest,
												 @PathVariable("accountId") Long accountId) {

		Boolean result = service.updateStudentAttendance(attendanceRequest,accountId);

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}

	@GetMapping("/getAttendance/{accountId}")
	public ResponseEntity<StudentAttendanceResponse> getStudentAttendanceResponse(
			@PathParam("divisionId") long divisionId, @PathParam("classId") long classId,
			@PathParam("subjectId") long subjectId,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@PathVariable("accountId") Long accountId) {

		StudentAttendanceResponse response = service.getStudentAttendance(divisionId, classId, subjectId, date,accountId);
		return new ResponseEntity<StudentAttendanceResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<List<Attendance>> getAllAttendance() {

		try {
			List<Attendance> all = service.getAllAttendance();

			return new ResponseEntity<List<Attendance>>(all, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Attendance>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/getAll/{accountId}")
	public PaginatedResponse<Attendance> getAll(@PathVariable("accountId") Integer accountId,
											@RequestBody PaginationRequest paginationRequest) {
		return service.getAll(paginationRequest, accountId);
	}

	@GetMapping("/class-division/{accountId}")
	public ResponseEntity<List<ClassDivisionResponse>> getClassDivisionByAccountId(@PathVariable Long accountId) {
		List<ClassDivisionResponse> list = service.getAllClassAndDivisionByAccountId(accountId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}




}
