package com.scm.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.app.model.StudentAttendanceMapping;

@Repository
public interface StudentAttendanceMappingRepo extends JpaRepository<StudentAttendanceMapping, Long> {

	StudentAttendanceMapping getByAttendanceIdAndStudentId(Long attendanceId, Long studentId);

	List<StudentAttendanceMapping> getByAttendanceId(Long id);

}
