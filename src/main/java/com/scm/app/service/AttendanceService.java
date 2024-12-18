package com.scm.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Attendance;
import com.scm.app.model.Student;
import com.scm.app.model.StudentAttendanceMapping;
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
	StudentRepo studentRepo;

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

		Attendance attendance = attendanceRepo.getBySchooldClassIdAndDivisionIdAndAttendanceDateAndSubjectId(
				updateRequest.getClassId(), updateRequest.getDivisionId(), updateRequest.getAttendanceDate(),
				updateRequest.getSubjectId());
		StudentAttendanceMapping stm = attendanceMappingRepo.getByAttendanceIdAndStudentId(attendance.getId(),
				updateRequest.getSubjectId());
		stm.setPresent(true);
		attendanceMappingRepo.save(stm);
		return true;

	}

	public StudentAttendanceResponse getStudentAttendance(long divisionId, long classId, long subjectId, Date date) {
		Attendance attendance = attendanceRepo.getBySchooldClassIdAndDivisionIdAndAttendanceDateAndSubjectId(classId,
				divisionId, date, subjectId);
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

			List<Student> students = studentRepo.getByDivisionIdAndClassId(divisionId, classId);
			List<StudentPresenty> stms = students.stream().map(ele -> Mappers.convertTo(ele))
					.collect(Collectors.toList());
			response.setStudentIds(stms);

		}
		return response;

	}

	public List<Attendance> getAllAttendance() {

		return attendanceRepo.findAll();
	}
}
