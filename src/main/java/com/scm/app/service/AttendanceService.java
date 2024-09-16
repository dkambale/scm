package com.scm.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Attendance;
import com.scm.app.model.StudentAttendanceMapping;
import com.scm.app.model.mapper.Mappers;
import com.scm.app.model.requests.StudentAttendanceRequest;
import com.scm.app.model.requests.StudentAttendanceUpdateRequest;
import com.scm.app.model.requests.StudentPresenty;
import com.scm.app.model.response.StudentAttendanceResponse;
import com.scm.app.repo.AttendanceRepo;
import com.scm.app.repo.StudentAttendanceMappingRepo;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepo attendanceRepo;

	@Autowired
	StudentAttendanceMappingRepo attendanceMappingRepo;

	public boolean updateBulkAttendance(StudentAttendanceRequest attendanceRequest) {

		Attendance attendance = new Attendance();
		attendance.setAttendanceDate(attendanceRequest.getAttendanceDate());
		attendance.setDivisionId(attendanceRequest.getDivisionId());
		attendance.setSchooldClassId(attendanceRequest.getClassId());
		attendance.setSubjectId(attendance.getSubjectId());
		attendance = attendanceRepo.save(attendance);

		List<StudentAttendanceMapping> attendanceMapping = new ArrayList<>();
		for (StudentPresenty sp : attendanceRequest.getStudentIds()) {
			StudentAttendanceMapping stm = new StudentAttendanceMapping();
			stm.setAttendanceId(attendance.getId());
			stm.setPresent(sp.getIsPresent());
			stm.setStudentId(sp.getStudentId());
			attendanceMapping.add(stm);
		}
		if (!attendanceMapping.isEmpty()) {
			attendanceMappingRepo.saveAll(attendanceMapping);
		}

		return true;
	}

	public boolean updateStudentAttendance(StudentAttendanceUpdateRequest updateRequest) {

		Attendance attendance = attendanceRepo.getByDivisionIdAndAttendanceDateAndSubjectId(
				updateRequest.getDivisionId(), updateRequest.getAttendanceDate(), updateRequest.getSubjectId());
		StudentAttendanceMapping stm = attendanceMappingRepo.getByAttendanceIdAndStudentId(attendance.getId(),
				updateRequest.getSubjectId());
		stm.setPresent(true);
		attendanceMappingRepo.save(stm);
		return true;

	}

	public StudentAttendanceResponse getStudentAttendance(long divisionId, long classId, long subjectId, Date date) {
		Attendance attendance = attendanceRepo.getByDivisionIdAndAttendanceDateAndSubjectId(divisionId, date,
				subjectId);
		List<StudentAttendanceMapping> stms = attendanceMappingRepo.getByAttendanceId(attendance.getId());
		List<StudentPresenty> sps = stms.stream().map(ele -> Mappers.convertTo(ele)).collect(Collectors.toList());
		StudentAttendanceResponse response = new StudentAttendanceResponse();
		response.setClassId(classId);
		response.setDivisionId(divisionId);
		response.setSubjectId(subjectId);
		response.setAttendanceDate(date);
		response.setStudentIds(sps);
		return null;
	}
}
