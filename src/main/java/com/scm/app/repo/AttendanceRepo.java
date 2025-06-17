package com.scm.app.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scm.app.model.Attendance;

public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

	@Query(" from Attendance a where a.schooldClassId =:schooldClassId and a.divisionId =:divisionId and a.attendanceDate=:attendanceDate and a.subjectId =:subjectId")
	Attendance getBySchooldClassIdAndDivisionIdAndAttendanceDateAndSubjectId(Long schooldClassId, Long divisionId,
			Date attendanceDate, Long subjectId);

    Page<Attendance> findByAccountId(Integer accountId, Pageable pageable);


}
