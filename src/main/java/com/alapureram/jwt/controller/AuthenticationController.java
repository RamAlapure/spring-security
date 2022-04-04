package com.alapureram.jwt.controller;

import com.alapureram.jwt.model.Consumer;
import com.alapureram.jwt.model.TokenDetails;
import com.alapureram.jwt.service.ConsumerService;
import com.alapureram.jwt.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("token")
    public ResponseEntity<TokenDetails> token(@RequestParam("id") String id, @RequestParam("secret") String secret) {
        Consumer consumer = consumerService.find(id);
        return ResponseEntity.ok(tokenService.create(consumer));
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("api/service")
    public String service(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name + "!!";
    }

    @GetMapping("api/hello")
    public String helloWorld(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name + "!!";
    }

}
