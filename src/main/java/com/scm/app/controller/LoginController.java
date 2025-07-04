package com.scm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.AuthResponse;
import com.scm.app.model.UserLoginInfo;
import com.scm.app.service.LoginService;

@RestController
@RequestMapping(name = "api/users", value = "api/users")
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping(name = "/login", value = "/login")
	public ResponseEntity<AuthResponse> login(@RequestBody UserLoginInfo userLoginInfo) {

		AuthResponse result = loginService.login(userLoginInfo.getUserName(), userLoginInfo.getPassword(),
				userLoginInfo.getType());

		return new ResponseEntity<AuthResponse>(result, HttpStatus.OK);
	}

}
