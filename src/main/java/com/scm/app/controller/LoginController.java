package com.scm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.UserLoginInfo;
import com.scm.app.service.LoginService;

@RestController
@RequestMapping(name = "/user", value = "/user")
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping(name = "/login", value = "/login")
	public ResponseEntity<Boolean> login(UserLoginInfo userLoginInfo) {

		Boolean result = loginService.login(userLoginInfo.getUserName(), userLoginInfo.getPassword(),
				userLoginInfo.getType());

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
