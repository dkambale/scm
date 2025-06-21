package com.scm.app.controller;


import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scm.app.model.User;
import com.scm.app.service.UserService;

@RestController
@RequestMapping(name = "api/users", value = "api/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping(name = "/save", value = "/save")
    public ResponseEntity<User> saveUser(@RequestBody User usr) {

        try {
            User user = service.saveUser(usr);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getAll/{accountId}")
    public PaginatedResponse<User> getAll(@PathVariable("accountId") Integer accountId,
                                          @RequestBody PaginationRequest paginationRequest,
                                          @RequestParam(value = "type", required = false) String type) {
        return service.getAll(paginationRequest, accountId,type);
    }

    @GetMapping("/getById")
    public User getById(@RequestParam("id") Long id) {
        return service.getById(id);
    }

    @PutMapping(name = "/update", value = "/update")
    public ResponseEntity<User> updateInstitute(@RequestBody User usr1) {
        try {
            User user1 = service.updateUser(usr1);
            return new ResponseEntity<User>(user1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") Long id) {
        return service.deleteById(id);
    }

}
