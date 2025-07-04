package com.scm.app.repo;

import java.util.Date;
import java.util.List;

import com.scm.app.model.response.ClassDivisionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scm.app.model.Attendance;

public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

	@Query(" from Attendance a where a.schooldClassId =:schooldClassId and a.divisionId =:divisionId and a.attendanceDate=:attendanceDate and a.subjectId =:subjectId and a.accountId =:accountId")
	Attendance getBySchooldClassIdAndDivisionIdAndAttendanceDateAndSubjectIdAndAccountId(Long schooldClassId, Long divisionId,
			Date attendanceDate, Long subjectId,Long accountId);

	Attendance getBySchooldClassIdAndDivisionIdAndAttendanceDateAndSubjectId(Long schooldClassId, Long divisionId,
																			 Date attendanceDate, Long subjectId);
    Page<Attendance> findByAccountId(Integer accountId, Pageable pageable);


	@Query("SELECT DISTINCT new com.scm.app.model.ClassDivisionStudent(a.schooldClassId, a.divisionId) " +
			"FROM Attendance a WHERE a.accountId = :accountId")
	List<ClassDivisionResponse> findDistinctClassAndDivisionByAccountId(Long accountId);

}
