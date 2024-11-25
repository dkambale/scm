package com.scm.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Dashboard;
import com.scm.app.model.enums.DashboardNameEnum;
import com.scm.app.repo.StudentRepo;
import com.scm.app.repo.SubjectRepo;
import com.scm.app.repo.TeacherRepo;

@Service
public class DashboardService {

	@Autowired
	TeacherRepo teacherRepo;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	SubjectRepo subjectRepo;

	public Dashboard getDashboard(String type) {
		Dashboard dasahbaord = new Dashboard();
		Map<String, String> map = new HashMap<>();
		long teacherCount = teacherRepo.count();
		map.put(DashboardNameEnum.TeacherCount.name(), "" + teacherCount);
		long studentCount = studentRepo.count();
		map.put(DashboardNameEnum.StudentCount.name(), "" + studentCount);
		long subjectCount = subjectRepo.count();
		map.put(DashboardNameEnum.SubjectCount.name(), "" + subjectCount);
		dasahbaord.setNameVsValue(map);
		return dasahbaord;
	}

}
