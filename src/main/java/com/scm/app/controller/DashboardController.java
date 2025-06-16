package com.scm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.app.model.Dashboard;
import com.scm.app.service.DashboardService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(name = "api/dashboard", value = "api/dashboard")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;

	@GetMapping(name = "/getCounts/{accountId}", value = "/getCounts/{accountId}")
	public ResponseEntity<Dashboard> getDashboard(@PathParam(value = "type") String type,
	@PathVariable("accountId") Integer accountId) {

		try {
			Dashboard dashbaord = dashboardService.getDashboard(type,accountId);
			return new ResponseEntity<Dashboard>(dashbaord, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Dashboard>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
