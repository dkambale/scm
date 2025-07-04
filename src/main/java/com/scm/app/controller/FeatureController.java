package com.scm.app.controller;


import com.scm.app.model.Feature;
import com.scm.app.model.requests.PaginationRequest;
import com.scm.app.model.response.PaginatedResponse;
import com.scm.app.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "api/features", value = "api/features")
public class FeatureController {

    @Autowired
    FeatureService service;

    @PostMapping(name = "/save", value = "/save")
    public ResponseEntity<Feature> saveFeature(@RequestBody Feature feature) {
        try {
            Feature saved = service.saveFeature(feature);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getAll/{accountId}")
    public PaginatedResponse<Feature> getAll(@PathVariable("accountId") Long accountId,
                                             @RequestBody PaginationRequest paginationRequest) {
        return service.getAll(paginationRequest, accountId);
    }

    @GetMapping("/getById")
    public Feature getById(@RequestParam("id") Long id) {
        return service.getById(id);
    }

    @PutMapping(name = "/update", value = "/update")
    public ResponseEntity<Feature> updateFeature(@RequestBody Feature feature) {
        try {
            Feature updated = service.updateFeature(feature);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("id") Long id) {
        return service.deleteById(id);
    }
}
