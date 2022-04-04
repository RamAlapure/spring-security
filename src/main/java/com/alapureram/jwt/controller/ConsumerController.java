package com.alapureram.jwt.controller;

import com.alapureram.jwt.model.Consumer;
import com.alapureram.jwt.request.ConsumerRequest;
import com.alapureram.jwt.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@RequestMapping("/api/consumers")
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consumer> create(@RequestBody ConsumerRequest consumerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.save(consumerRequest));
    }
}
