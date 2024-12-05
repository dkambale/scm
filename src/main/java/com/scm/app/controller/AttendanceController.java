package com.scm.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Attendance;
import com.scm.app.model.requests.StudentAttendanceRequest;
import com.scm.app.model.requests.StudentAttendanceUpdateRequest;
import com.scm.app.model.response.StudentAttendanceResponse;
import com.scm.app.service.AttendanceService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(name = "/attendance", value = "/attendance")
public class AttendanceController {

	@Autowired
	AttendanceService service;

	@PostMapping(name = "/updateBulk", value = "/updateBulk")
	public ResponseEntity<Boolean> updateBulkAttendance(@RequestBody StudentAttendanceRequest attendanceRequest) {

		Boolean result = service.updateBulkAttendance(attendanceRequest);

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}

	@PostMapping(name = "/updateStudent", value = "/updateStudent")
	public ResponseEntity<Boolean> updateStudent(@RequestBody StudentAttendanceUpdateRequest attendanceRequest) {

		Boolean result = service.updateStudentAttendance(attendanceRequest);

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}

	@GetMapping(name = "/getAttendance")
	public ResponseEntity<StudentAttendanceResponse> getStudentAttendanceResponse(
			@PathParam("divisionId") long divisionId, @PathParam("classId") long classId,
			@PathParam("subjectId") long subjectId, @PathParam("date") Date date) {

		StudentAttendanceResponse response = service.getStudentAttendance(divisionId, classId, subjectId, date);
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

}
