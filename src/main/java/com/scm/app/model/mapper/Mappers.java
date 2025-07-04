package com.scm.app.model.mapper;

import com.scm.app.model.Student;
import com.scm.app.model.StudentAttendanceMapping;
import com.scm.app.model.User;
import com.scm.app.model.requests.StudentPresenty;

public class Mappers {

	public static StudentPresenty convertTo(StudentAttendanceMapping sam) {

		StudentPresenty sp = new StudentPresenty();
		sp.setIsPresent(sam.isPresent());
		sp.setStudentId(sam.getStudentId());
		sp.setStudentName(sam.getStudentName());
		return sp;

	}

	public static StudentPresenty convertTo(User ele) {
		
		StudentPresenty sp = new StudentPresenty();
		sp.setIsPresent(false);
		sp.setStudentId(ele.getId());
		sp.setStudentRollNo(ele.getRollNo());
		sp.setStudentName(ele.getUserName());

		return sp;
	}

}
