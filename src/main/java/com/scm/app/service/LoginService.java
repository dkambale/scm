package com.scm.app.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.AuthResponse;
import com.scm.app.model.Role;
import com.scm.app.model.Student;
import com.scm.app.model.Teacher;
import com.scm.app.model.enums.LoginTypeEnum;
import com.scm.app.model.enums.StatusCode;
import com.scm.app.repo.RoleRepo;
import com.scm.app.repo.StudentRepo;
import com.scm.app.repo.TeacherRepo;

@Service
public class LoginService {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	TeacherRepo teacherRepo;
	
	@Autowired
	RoleRepo roleRepo;

	public AuthResponse login(String username, String password, String type) {

		AuthResponse response = new AuthResponse();
		response.setStatus(StatusCode.FAIL.toString());
		response.setStatusCode(402);

		if (type.equals(LoginTypeEnum.STUDENT.toString())) {
			Student student = studentRepo.getByUserNameAndPassword(username, password);
			if (student != null) {
				response.setStatus(StatusCode.SUCCESS.toString());
				response.setStatusCode(200);
				response.setAccessToken(UUID.randomUUID().toString());
				Role role = roleRepo.findById(student.getRoleId()).get();
				response.setRole(role);
				
			}
		} else {
			Teacher teacher = teacherRepo.getByUserNameAndPassword(username, password);
			if (teacher != null) {
				response.setStatus(StatusCode.SUCCESS.toString());
				response.setStatusCode(200);
				response.setAccessToken(UUID.randomUUID().toString());
				Role role = roleRepo.findById(teacher.getRoleId()).get();
				response.setRole(role);
			}
		}

		return response;

	}

}
