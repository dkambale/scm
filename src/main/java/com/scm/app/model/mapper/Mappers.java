package com.scm.app.model.mapper;

import com.scm.app.model.StudentAttendanceMapping;
import com.scm.app.model.requests.StudentPresenty;

public class Mappers {

	public static StudentPresenty convertTo(StudentAttendanceMapping sam) {

		StudentPresenty sp = new StudentPresenty();
		sp.setIsPresent(sam.isPresent());
		sp.setStudentId(sam.getStudentId());
		return sp;

	}

}
