package com.scm.app.model.requests;

import java.util.Date;
import java.util.List;

public class StudentAttendanceRequest {

	Date attendanceDate;
	List<StudentPresenty> studentIds;
	Long classId;
	Long divisionId;
	Long teacherId;
	Long subjectId;

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public List<StudentPresenty> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<StudentPresenty> studentIds) {
		this.studentIds = studentIds;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

}
