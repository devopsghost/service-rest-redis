package org.chintanu.service_rest_redis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/getUser")
    public ResponseEntity<String> getUser() {

        return ResponseEntity.ok("User 1");
    }

    @GetMapping("/getVersion")
    public ResponseEntity<String> getVersion() {

        return ResponseEntity.ok("Version 3");
    }
}
