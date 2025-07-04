package com.scm.app.model.requests;

public class StudentPresenty {

	Long studentId;
	Boolean isPresent;
	String studentName;
	Long studentRollNo;

	public Long getStudentRollNo() {
		return studentRollNo;
	}
	public void setStudentRollNo(Long studentRollNo) {
		this.studentRollNo = studentRollNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Boolean getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}

}
