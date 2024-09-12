package com.scm.app.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.AuthResponse;
import com.scm.app.model.Student;
import com.scm.app.model.Teacher;
import com.scm.app.model.enums.StatusCode;
import com.scm.app.repo.StudentRepo;
import com.scm.app.repo.TeacherRepo;

@Service
public class LoginService {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	TeacherRepo teacherRepo;

	public AuthResponse login(String username, String password, String type) {

		AuthResponse response = new AuthResponse();
		response.setStatus(StatusCode.FAIL.toString());
		response.setStatusCode(402);

		if (type.equals("STUDENT")) {
			Student student = studentRepo.getByUserNameAndPassword(username, password);
			if (student != null) {
				response.setStatus(StatusCode.SUCCESS.toString());
				response.setStatusCode(200);
				response.setAccessToken(UUID.randomUUID().toString());
			}
		} else {
			Teacher teacher = teacherRepo.getByUserNameAndPassword(username, password);
			if (teacher != null) {
				response.setStatus(StatusCode.SUCCESS.toString());
				response.setStatusCode(200);
				response.setAccessToken(UUID.randomUUID().toString());
			}
		}

		return response;

	}

}
