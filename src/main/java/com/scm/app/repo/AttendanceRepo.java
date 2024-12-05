package com.scm.app.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.app.model.Attendance;

public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

	Attendance getByClassIdAndDivisionIdAndAttendanceDateAndSubjectId(Long classId, Long divisionId,
			Date attendanceDate, Long subjectId);

}
