package com.scm.app.model.enums;

public enum DashboardNameEnum {

	TeacherCount("Teacher Count"), 
	StudentCount("Student Count"), 
	SubjectCount("Subject Count");

	String name;

	DashboardNameEnum(String name) {
		this.name = name;
	}
}
