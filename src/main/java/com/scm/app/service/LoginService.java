package com.scm.app.service;

import java.util.UUID;

import com.scm.app.model.*;
import com.scm.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    public AuthResponse login(String username, String password, String type) {
        AuthResponse response = new AuthResponse();
        response.setStatus(StatusCode.FAIL.toString());
        response.setStatusCode(402);
        User user = userRepo.getByUserNameAndPassword(username, password);
        if (user != null) {
            response.setStatus(StatusCode.SUCCESS.toString());
            response.setStatusCode(200);
            response.setAccessToken(UUID.randomUUID().toString());
            response.setRole(user.getRole());
            response.setData(user);
        }

        return response;
    }

}
