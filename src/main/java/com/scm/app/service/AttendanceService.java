package com.scm.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.scm.app.model.*;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.repo.UserRepo;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.app.model.mapper.Mappers;
import com.scm.app.model.requests.StudentAttendanceRequest;
import com.scm.app.model.requests.StudentAttendanceUpdateRequest;
import com.scm.app.model.requests.StudentPresenty;
import com.scm.app.model.response.StudentAttendanceResponse;
import com.scm.app.repo.AttendanceRepo;
import com.scm.app.repo.StudentAttendanceMappingRepo;
import com.scm.app.repo.StudentRepo;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepo attendanceRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	StudentAttendanceMappingRepo attendanceMappingRepo;


	@Autowired
	private AuditLogger auditLogger;


	public boolean updateBulkAttendance(StudentAttendanceRequest attendanceRequest) {

		Attendance attendance = new Attendance();
		attendance.setAttendanceDate(attendanceRequest.getAttendanceDate());
		attendance.setDivisionId(attendanceRequest.getDivisionId());
		attendance.setSchooldClassId(attendanceRequest.getClassId());
		attendance.setSubjectId(attendance.getSubjectId());
		attendance.setAccountId(attendanceRequest.getAccountId());
		attendance.setClassName(attendanceRequest.getClassName());
		attendance.setDivisionName(attendanceRequest.getDivisionName());
		attendance.setSubjectName(attendanceRequest.getSubjectName());
		attendance = attendanceRepo.save(attendance);

		List<StudentAttendanceMapping> attendanceMapping = new ArrayList<>();
		for (StudentPresenty sp : attendanceRequest.getStudentIds()) {
			StudentAttendanceMapping stm = new StudentAttendanceMapping();
			stm.setAttendanceId(attendance.getId());
			stm.setPresent(sp.getIsPresent());
			stm.setStudentId(sp.getStudentId());
			stm.setStudentRollNo(sp.getStudentRollNo());
			stm.setAccountId(attendanceRequest.getAccountId().intValue());

			attendanceMapping.add(stm);
		}
		if (!attendanceMapping.isEmpty()) {
			attendanceMappingRepo.saveAll(attendanceMapping);
			//  Audit Log for UPDATE
			auditLogger.logAction(
					attendance.getAccountId(),
					attendance.getId(),
					"Attendance",
					"Edit",
					attendance.getCreatedBy()
			);
		}

		return true;
	}

	public boolean updateStudentAttendance(StudentAttendanceUpdateRequest updateRequest,Long accountId) {

		Attendance attendance = attendanceRepo.getBySchooldClassIdAndDivisionIdAndAttendanceDateAndSubjectIdAndAccountId(
				updateRequest.getClassId(), updateRequest.getDivisionId(), updateRequest.getAttendanceDate(),
				updateRequest.getSubjectId(),accountId);
		StudentAttendanceMapping stm = attendanceMappingRepo.getByAttendanceIdAndStudentId(attendance.getId(),
				updateRequest.getSubjectId());
		stm.setPresent(true);
		attendanceMappingRepo.save(stm);

		//  Audit Log for UPDATE
		auditLogger.logAction(
				attendance.getAccountId(),
				attendance.getId(),
				"Attendance",
				"Edit",
				attendance.getCreatedBy()
		);
		return true;

	}

	public StudentAttendanceResponse getStudentAttendance(long divisionId, long classId, long subjectId, Date date,Long accountId) {
		Attendance attendance = attendanceRepo.getBySchooldClassIdAndDivisionIdAndAttendanceDateAndSubjectIdAndAccountId(classId,
				divisionId, date, subjectId,accountId);
		StudentAttendanceResponse response = new StudentAttendanceResponse();
		response.setClassId(classId);
		response.setDivisionId(divisionId);
		response.setSubjectId(subjectId);
		response.setAttendanceDate(date);
		if (attendance != null) {
			List<StudentAttendanceMapping> stms = attendanceMappingRepo.getByAttendanceId(attendance.getId());
			List<StudentPresenty> sps = stms.stream().map(ele -> Mappers.convertTo(ele)).collect(Collectors.toList());
			response.setStudentIds(sps);
		} else {

			List<User> students = userRepo.getByDivisionIdAndClassIdAndAccountId(divisionId, classId,accountId);
			List<StudentPresenty> stms = students.stream().map(ele -> Mappers.convertTo(ele))
					.collect(Collectors.toList());
			response.setStudentIds(stms);

		}
		return response;

	}

	public List<Attendance> getAllAttendance() {

		return attendanceRepo.findAll();
	}

	public PaginatedResponse<Attendance> getAll(PaginationRequest request, Integer accountId) {

		Sort sort = request.getSortDir().equalsIgnoreCase("asc") ? Sort.by(request.getSortBy()).ascending() : Sort.by(request.getSortBy()).descending();
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Attendance> userPage =attendanceRepo.findByAccountId(accountId, pageable);
		PaginatedResponse<Attendance> response = new PaginatedResponse<>();
		response.setContent(userPage.getContent());
		response.setPageNumber(userPage.getNumber());
		response.setPageSize(userPage.getSize());
		response.setTotalElements(userPage.getTotalElements());
		response.setTotalPages(userPage.getTotalPages());
		response.setLastPage(userPage.isLast());

		return response;
	}
}
