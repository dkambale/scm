package com.scm.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Student;
import com.scm.app.model.Teacher;
import com.scm.app.repo.StudentRepo;
import com.scm.app.repo.TeacherRepo;

@Service
public class LoginService {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	TeacherRepo teacherRepo;

	public boolean login(String username, String password, String type) {

		if (type.equals("STU")) {
			Student student = studentRepo.getByUserNameAndPassword(username, password);
			if (student != null) {
				return true;
			}
		} else {
			Teacher teacher = teacherRepo.getByUserNameAndPassword(username, password);
			if (teacher != null) {
				return true;
			}
		}

		return false;

	}

}
